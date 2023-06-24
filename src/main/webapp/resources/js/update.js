"use strict";
const dataText = {
  province: "",
  district: "",
  wards: "",
};

$(".upload__img-wrap").css("border", "none");
const imageLength = $(".upload__img-box.dataFill").length;
var imgArray = [];

let i = 0;
const myArray = [...$(".upload__img-box.dataFill")];
myArray.forEach((item) => {
  imgArray[i] = item.dataset.imageid;
  i++;
});

(() => {
  return {
    getAddressHandler() {
      const province = document.getElementById("form-province");
      const district = document.getElementById("form-district");
      const commue = document.getElementById("form-commue");

      const provinceValue = province.dataset.value;
      const districtValue = district.dataset.value;
      const wardsValue = commue.dataset.value;

      dataText.province = provinceValue;
      dataText.district = districtValue;
      dataText.wards = wardsValue;

      const renderHTML = (arr, code, type) => {
        let result = "";
        arr.forEach((item) => {
          if (item.code == code) {
            result = item[type].reduce((acc, item) => {
              return acc + `<option value=${item.code}>${item.name}</option>`;
            }, "");
          }
        });
        return result;
      };
      fetch("https://provinces.open-api.vn/api/?depth=3")
        .then((response) => response.json())
        .then((data) => {
          const htmlProvince = data.reduce((acc, item) => {
            return acc + `<option value=${item.code}>${item.name}</option>`;
          }, "");

          const htmlCommue = data[0].districts[0].wards.reduce((acc, item) => {
            return acc + `<option value=${item.code}>${item.name}</option>`;
          }, "");

          province.innerHTML =
            '<option value="">Chọn Tỉnh, Thành Phố</option>' + htmlProvince;

          for (let option of province.options) {
            if (option.text === provinceValue) {
              option.setAttribute("selected", "selected");
              const provinceCode = option.value;
              const html = renderHTML(data, provinceCode, "districts");
              district.innerHTML =
                '<option value="">Chọn Quận, Huyện</option>' + html;
              for (let option of district.options) {
                if (option.text === districtValue) {
                  option.setAttribute("selected", "selected");
                  data.forEach((item) => {
                    if (item.code == provinceCode) {
                      const html = renderHTML(
                        item["districts"],
                        option.value,
                        "wards"
                      );
                      commue.innerHTML = html;
                      for (let option of commue.options) {
                        if (option.text === wardsValue) {
                          option.setAttribute("selected", "selected");
                        }
                      }
                    }
                  });
                }
              }
            }
          }
          province.onchange = (e) => {
            const code = e.target.value;
            dataText.province = province.options[province.selectedIndex].text;
            const html = renderHTML(data, code, "districts");
            district.innerHTML =
              '<option value="">Chọn Quận, Huyện</option>' + html;

            district.onchange = (e) => {
              const codeDistrict = e.target.value;
              dataText.district = district.options[district.selectedIndex].text;

              data.forEach((item) => {
                if (item.code == code) {
                  const html = renderHTML(
                    item["districts"],
                    codeDistrict,
                    "wards"
                  );
                  commue.innerHTML = html;
                }
              });
            };
            commue.onchange = (e) => {
              dataText.wards = commue.options[commue.selectedIndex].text;
            };
          };
          return htmlCommue;
        })
        .catch((err) => console.error(err));
    },
    uploadImageFileHandler() {
      jQuery(document).ready(function () {
        var imgWrap = "";
        let isNotiCase1 = false;
        let isNotiCase2 = false;

        $(".upload__inputfile").each(function () {
          $(this).on("change", function (e) {
            imgWrap = $(this).closest(".upload__box").find(".upload__img-wrap");
            var maxLength = $(this).attr("data-max_length");
            var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);

            filesArr.forEach(function (f, index) {
              if (!f.type.match("image.*")) {
                toast({
                  title: "Không đúng định dạng ảnh!",
                  message: "Vui lòng chọn đúng định dạng! ",
                  type: "error",
                  duration: 5000,
                });
                return;
              }

              if (imgArray.length > maxLength) {
                if (!isNotiCase1) {
                  isNotiCase1 = true;
                  toast({
                    title: "Quá số lượng ảnh quy định (6 ảnh) !",
                    message: "Một bài đăng tối đa chỉ được phép có 6 ảnh !",
                    type: "error",
                    duration: 5000,
                  });
                }
                return false;
              } else {
                var len = 0;
                for (var i = 0; i < imgArray.length; i++) {
                  if (imgArray[i] !== undefined) {
                    len++;
                  }
                }
                if (len > maxLength - 1) {
                  if (!isNotiCase2) {
                    isNotiCase2 = true;
                    toast({
                      title: "Quá số lượng ảnh quy định (6 ảnh) !",
                      message: "Một bài đăng tối đa chỉ được phép có 6 ảnh !",
                      type: "error",
                      duration: 5000,
                    });
                  }
                  return false;
                } else {
                  imgArray.push(f);
                  var reader = new FileReader();
                  reader.onload = function (e) {
                    var html =
                      "<div class='upload__img-box'><div style='background-image: url(" +
                      e.target.result +
                      ")' data-number='" +
                      $(".upload__img-close").length +
                      "' data-file='" +
                      f.name +
                      "' class='img-bg'><div class='upload__img-close'></div></div></div>";
                    imgWrap.append(html);
                  };
                  reader.readAsDataURL(f);
                }
              }
            });
            const videoTag = $("#video-tag");
            const videoInput = $("#video_input");
            videoInput.on("change", (e) => {
              videoTag.attr("src", URL.createObjectURL(event.target.files[0]));
              $(".video-placeholder").addClass("hide");
            });
            $(".upload__img-wrap").css("border", "none");
          });
        });
        $("body").on("click", ".upload__img-close", function (e) {
          console.log(imgArray);

          var fileData = $(this).parent().data("file");

          for (var i = 0; i < imgArray.length; i++) {
            if (typeof imgArray[i] == "string") {
              if (imgArray[i] == fileData) imgArray.splice(i, 1);
            } else {
              if (imgArray[i].name === fileData) {
                imgArray.splice(i, 1);
                break;
              }
            }
          }

          $(this).parent().parent().remove();
        });
      });
    },
    uploadVideoHandler() {
     const videoTag = $("#video-tag");
          const closeVideoBtn = $('.close-video');
          closeVideoBtn.click(()=>{
            $(".video-placeholder").removeClass("hide");
          })
          const videoInput = $("#video_input");
          videoInput.on("change", (e) => {
            videoTag.attr("src", URL.createObjectURL(event.target.files[0]));
            $(".video-placeholder").addClass("hide");
          });
    },
    showFormSignInUpHandler() {
      const loginForm = document.querySelector(".form-login");
      const loginBtn = document.querySelector(".btn-login");
      const loginCloseBtn = document.querySelector(".btn-close");
      const loginOverlay = document.querySelector(".overlay-login");
      const formLogin = document.querySelector(".login .form");
      loginBtn.onclick = () => {
        loginForm.classList.add("active");
      };
      loginCloseBtn.onclick = (e) => {
        loginForm.classList.remove("active");
      };
      loginOverlay.onclick = () => {
        loginForm.classList.remove("active");
      };
      formLogin.onclick = (e) => {
        e.stopPropagation();
      };

      //FORM SIGN UP
      const signForm = document.querySelector(".form-sign-up");
      const signBtn = document.querySelector(".button--hl");
      const signCloseBtn = document.querySelector(".btn-close");
      const signOverlay = document.querySelector(".overlay-sign");
      const formSign = document.querySelector(".sign .form");
      signBtn.onclick = () => {
        signForm.classList.add("active");
      };
      signCloseBtn.onclick = (e) => {
        signForm.classList.remove("active");
      };
      signOverlay.onclick = () => {
        signForm.classList.remove("active");
      };
      formSign.onclick = (e) => {
        e.stopPropagation();
      };

      // gototop && header sticky handler
      const gototop = document.querySelector(".gototop");
      const headerTopHeight =
        document.querySelector(".header-login").offsetHeight;
      const headerNav = document.querySelector(".header-nav");
      const headerNavHeight = headerNav.offsetHeight;
      const headerLogin = document.querySelector(".header-login");

      document.onscroll = function () {
        if (window.scrollY > 400) {
          gototop.classList.remove("hide");
        } else {
          gototop.classList.add("hide");
        }

        if (window.scrollY > headerTopHeight) {
          headerNav.classList.add("fixed");
          headerLogin.style.marginBottom = `${headerNavHeight}px`;
        } else {
          headerNav.classList.remove("fixed");
          headerLogin.style.marginBottom = "0px";
        }
      };
    },
    validationAndSubmitFormHandler() {
      $("#fileUpload").on("change", () => {
        $(".imageMessage").text("");
      });

      Validator({
        form: "#info-form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
          Validator.isRequired(
            "#form-title",
            "Vui lòng nhập tiêu đề cho bài đăng !"
          ),
          Validator.isRequired(
            "#form-province",
            "Vui lòng chọn địa chỉ cho bài đăng !"
          ),
          Validator.isRequired(
            "#form-district",
            "Vui lòng chọn địa chỉ cho bài đăng !"
          ),
          Validator.isRequired(
            "#form-commue",
            "Vui lòng chọn địa chỉ cho bài đăng !"
          ),
          Validator.isRequired(
            "#form-desc",
            "Vui lòng nhập mô tả cho bài đăng !"
          ),
          Validator.isPrice(
            "#form-price",
            "Giá của phòng phải là số dương và lớn hơn 0.2 triệu !"
          ),
          Validator.isNumber(
            "#form-area",
            "Diện tích của phòng phải là số dương và lớn hơn 10 !"
          ),
        ],
        onSubmit: function (data) {
          let ID = $(".main").attr("id");
          let URL = "../../api/post-upload-update-no-video/" + ID;

          const formData = new FormData();
          const imageInput = $("#fileUpload");
          const videoInput = $("#video_input");
          const imageFiles = imageInput[0].files;
          const videoFile = videoInput[0].files[0];

          let desc = $("#form-desc").val().trim();
          desc = desc.replaceAll("\n", "//#");

          dataText.street = $("#form-street").val();
          dataText.title = $("#form-title").val().trim().replaceAll("\n", " ");
          dataText.description = desc;
          dataText.price = $("#form-price").val();
          dataText.area = $("#form-area").val();

          const IDImgList = [];
          imgArray.forEach((file) => {
            if (typeof file == "string") {
              IDImgList.push(file);
            } else {
              formData.append("images", file);
            }
          });

            if(!$(".video-placeholder").hasClass('hide')){
              formData.append("isRemoveVideo",JSON.stringify({isRemoveVideo: "true"}));
            }

          const imageIDs = IDImgList.join("_");
          dataText.imageIDs = imageIDs;

          formData.append("info", JSON.stringify(dataText));
            console.log(videoFile);
          if (videoFile && $(".video-placeholder").hasClass('hide')) {
            formData.append("video", videoFile);
            URL = "../../api/post-upload-update/" + ID;
          }



          if (!imgArray.length) {
            $(".imageMessage").text(
              "Yêu cầu ít nhất 1 ảnh đại diện cho tin đăng!"
            );
            toast({
              title: "Các trường dữ liệu nhập không hợp lệ!",
              message: "Vui lòng chỉnh sửa lại nội dung bài đăng !",
              type: "error",
              duration: 5000,
            });
          } else {
           console.log(URL);
            $.ajax({
              url: URL,
              type: "POST",
              data: formData,
              enctype: "multipart/form-data",
              processData: false,
              contentType: false,
              cache: false,
              success: function (data) {
                toast({
                  title: "Cập nhật dữ liệu thành công !",
                  message:
                    "Các thay đổi đã được lưu lại thành công!",
                  type: "success",
                  duration: 5000,
                });
              },
              error: function () {
                toast({
                  title: "Có lỗi xảy ra khi gửi request về server !",
                  message: "Vui lòng liên hệ quản trị viên để giải quyết !",
                  type: "error",
                  duration: 5000,
                });
              },
            });
          }
        },
        onFormInvalid() {
          if (!$("#fileUpload")[0].files.length) {
            $(".imageMessage").text(
              "Yêu cầu ít nhất 1 ảnh đại diện cho tin đăng!"
            );
          }
          toast({
            title: "Các trường dữ liệu nhập không hợp lệ!",
            message: "Vui lòng chỉnh sửa lại nội dung bài đăng !",
            type: "error",
            duration: 5000,
          });
        },
      });
    },
    run() {
      this.getAddressHandler();
      this.uploadImageFileHandler();
      this.uploadVideoHandler();
      this.showFormSignInUpHandler();
      this.validationAndSubmitFormHandler();
    },
  };
})().run();
