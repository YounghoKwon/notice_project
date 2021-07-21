
function createNotice() {
    var title = $('#title').val();
    var contents = $('#contents_textarea').val();
    var data = new Object();
    data.title = title;
    data.contents = contents;
    $.ajax({
        url: "/api/notice/",
        contentType: 'application/json',
        type:"POST",
        data: JSON.stringify(data),
    }).done(function (response) {
        alert("등록에 성공하셧습니다.");
        location.href = "/notice/list/";
    }).fail(function(xhr, status, errorThrown) {
        if(xhr.status === 400){
            var field = xhr.responseJSON.errors[0].field;
            var message = xhr.responseJSON.errors[0].defaultMessage;
            alert((field === 'title' ? '제목' : "") + message );
        }
    });
}
