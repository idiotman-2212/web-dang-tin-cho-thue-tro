// Post Handler ==================> 
$(document).ready(function () {
    $('#post-table').DataTable();
});
// Post Handler =================>
$(() => {
    const accDetail = $('.detail-info');
    const infoBtn = $('.info-detail-btn');
    const clostBtn = $('.di-close-btn'); 
    const cardInfo = $('.di-wrapper');
    const overlay = $('.overlay');
    infoBtn.click(() => {
        accDetail.addClass('active');
    })
    clostBtn.click(() => {
        accDetail.removeClass('active');
    })
    overlay.click(() => {
        accDetail.removeClass('active');
    })
    cardInfo.click((e) => {
        e.stopPropagation();
    })
})