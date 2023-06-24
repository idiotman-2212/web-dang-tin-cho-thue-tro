$("#upload-file-input").on("submit", function (e) {
    e.preventDefault();
    var formData = new FormData();
    let a= $("#file")[0].files;
    for(let i=0;i<a.length;i++)
    {formData.append("file",a[i]);}
    formData.append("data",data)
    $.ajax({
      url: "../api/upload",
      type: "POST",
      data: formData,
      enctype: "multipart/form-data",
      processData: false,
      contentType: false,     
      cache: false,
      success: function (data) {
        // Handle upload success
        console.log(data);
        $("#upload-file-message").text("File succesfully uploaded");
      },
      error: function () {
        // Handle upload error
        $("#upload-file-message").text(
          "File not uploaded (perhaps it's too much big)"
        );
      },
    });
  });