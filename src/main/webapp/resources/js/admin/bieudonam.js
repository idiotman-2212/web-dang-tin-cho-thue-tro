reload()
function reload(){
$.ajax({
  url: "http://localhost:3000/api/baitheonam",
  type: "POST",
  processData: false,
  contentType: "application/json",
  data: JSON.stringify({'data': $( "#year" ).val()}),
  cache: false,
  success: function (result) {
    let myChart = document.getElementById('myBarChart').getContext('2d');
Chart.defaults.global.defaultFontFamily = 'Lato';
Chart.defaults.global.defaultFontSize = 18;
Chart.defaults.global.defaultFontColor = '#000000';
let label = new Array();
data= new Array();
for(let i =1; i<13; i++) label.push(i);
for(let i=1; i<13; i++) data.push(0);
result.forEach((value,index) => {
data[value.thang-1]= value.soluong
});
let massPopChart = new Chart(myChart, {
type:'line',
data:{
labels:label,
datasets:[{
  label:'Bài trong tháng',
  data:data,
  backgroundColor:
    'rgba(54, 162, 235, 0.6)',
  borderWidth:1,
  borderColor:'#777',
  hoverBorderWidth:3,
  hoverBorderColor:'#000',
  tension: 0.1
  }]
},
options:{
title:{
  display:true,
  text:`Số lượng bài viết trong năm ${$( "#year" ).val()}`,
  fontSize:25
},
legend:{
  display:false,
  position:'right',
  labels:{
    fontColor:'#000'
  }
},
layout:{
  padding:{
    left:50,
    right:0,
    bottom:0,
    top:0
  }
},
scales: {
yAxes: [{
    scaleLabel: {
    display: true,
    labelString: 'Số lượng bài'
  }
}],
xAxes: [{
    scaleLabel: {
    display: true,
    labelString: 'Tháng'
  }
}]
},
tooltips:{
  enabled:true
}
},
 
});
  },
  error: function () {
   alert("Có lỗi xảy ra")
  },
}

)
}