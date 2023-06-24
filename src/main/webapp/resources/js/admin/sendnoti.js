let formdata= new FormData()
function getid(id){
  formdata.append('id',id);
}
function send(){
     formdata.append('title',$('#notiTitle').val());
     formdata.append('content', $('#noti-content').val());
     console.log(formdata.values());
    $.ajax({
        url: "../api/sendnoti",
        type: "POST",
        data: formdata,
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
          $('#notifcation-modal').css('display','none');
          $('.modal-backdrop').css('display','none');
          alert(`Thông báo
           ${data}`);
          toast({
            title: "Gửi thành công!",
            message:
              "Thông báo đã được gửi đến người dùng",
            type: "success",
            duration: 5000,

          });
        },
        error: function () {
          // Handle upload error
          $('.modal-backdrop').css('display','none');
          $('#notifcation-modal').css('display','none');
          alert('thất bại vui lòng liên hệ quản trị viên hệ thống')
          toast({
            title: "Có lỗi xảy ra khi gửi request về server !",
            message: "Vui lòng liên hệ quản trị viên để giải quyết !",
            type: "error",
            duration: 5000,
          });
        },
      });
}