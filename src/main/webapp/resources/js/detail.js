
const detailList = $(".dp-list");
const detailItem = $(".dp-item");
const viewWidth = detailList[0].offsetWidth;
const detailItemCount = detailList[0].childElementCount;
const leftCtl = $(".dp-left");
const rightCtl = $(".dp-right");

let currentIndexItem = 0;

leftCtl.click(function () {
  if (currentIndexItem > 0) {
    currentIndexItem--;
    detailList[0].style.transform = `translateX(-${currentIndexItem * 100}%)`;
  }
  currentIndexItem === 0 && this.classList.add("disable");

  rightCtl.hasClass("disable") && rightCtl.removeClass("disable");

});
rightCtl.click(function () {
  if (currentIndexItem < detailItemCount - 1) {
    currentIndexItem++;
    detailList[0].style.transform = `translateX(-${currentIndexItem * 100}%)`;
  }
  currentIndexItem === detailItemCount - 1 && this.classList.add("disable");
  leftCtl.hasClass("disable") && leftCtl.removeClass("disable");
});