const gototop = document.querySelector(".gototop");
const headerTopHeight = document.querySelector(".header-login").offsetHeight;
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



