$(() => {
  if ($(".acc-name > span").text()) {
    $(".acc-wrapper").removeClass("notSigned");
    $(".acc-wrapper").addClass("signed");
  } else {
    $(".acc-wrapper").removeClass("signed");
    $(".acc-wrapper").addClass("notSigned");
  }

  $(".acc-control").click((e) => {
    e.stopPropagation();
    $(".acc-setting").toggleClass("active");
  });
  $("#app").click(() => {
    $(".acc-setting").hasClass("active") &&
      $(".acc-setting").removeClass("active");
  });
  $(".acc-setting").click((e) => {
    e.stopPropagation();
  });
});