$(function(){
	// 日記の一覧表示
	$.ajax({
		type: 'GET',
		url: 'api/diary',
	}).done(function(data){
		console.log(data);
		data.forEach(function(value) {
			var created_at = new Date(value.created_at);
			$('#contents-list').append('<div class="diary-content"><div class="post-date">' 
					+ created_at.getFullYear() + '-' + (created_at.getMonth() + 1) + '-' + created_at.getDate() + ' '
					+ created_at.getHours() + ':' + created_at.getMinutes() + ':' + created_at.getSeconds() 
					+ '</div><p>' 
					+ value.content 
					+ '</p></div>');
		});
	}).fail(function(msg){
		console.log('Ajax Error');
	});

	// 投稿内容を登録する
	$('#diary_form').submit(function(event) {
	    event.preventDefault();
	    var form = $(this);
	    var button = form.find('button');
	    var content = $('#content').val();
	    if (!content) {
	    	alert('投稿内容を入力してください。');
	    	return;
	    }
	    var params = {
	    		'content': content
	    		};
	    $.ajax({
	          type: 'POST',
	          url: 'api/diary',
	          data: JSON.stringify(params),
	          dataType:'json',
	          contentType: 'application/json',
	          timeout: 10000,
	          beforeSend: function(xhr, settings) {
	              button.attr('disabled', true);
	          },
	          complete: function(xhr, textStatus) {
	              button.attr('disabled', false);
	          },
	        }).done(function(data){
	        	var created_at = new Date(data.created_at);
				$('#contents-list').prepend('<div class="diary-content"><div class="post-date">' 
						+ created_at.getFullYear() + '-' + (created_at.getMonth() + 1) + '-' + created_at.getDate() + ' '
						+ created_at.getHours() + ':' + created_at.getMinutes() + ':' + created_at.getSeconds() 
						+ '</div><p>' 
						+ data.content 
						+ '</p></div>');
				$('#content').val("");  
	        }).fail(function(msg) {
	          console.log('Ajax Error');
        });
	});
	
})