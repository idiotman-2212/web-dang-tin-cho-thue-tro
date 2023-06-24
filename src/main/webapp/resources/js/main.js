window.onload = ()=>{
      loginForm = document.querySelector(".form-login");
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
      loginOverlay.onmousedown = () => {
        loginForm.classList.remove("active");
      };
      formLogin.onmousedown = (e) => {
        e.stopPropagation();
      };
}

