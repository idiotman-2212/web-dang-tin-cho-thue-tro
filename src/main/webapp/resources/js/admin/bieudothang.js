  function daysInMonth (month, year) {
    return new Date(year, month, 0).getDate();
     }

      gettheothang();
        function gettheothang(){
          let year=  $( "#year2" ).val();
          let month =$('#month').val();
            $.ajax({
              url: "http://localhost:3000/api/baitheothang",
              type: "POST",
              processData: false,
              contentType: "application/json",
              data: JSON.stringify({'year':year,'month':month} ),
              cache: false,
              success: function (result) {
        let myChart = document.getElementById('CharMonth').getContext('2d');
        Chart.defaults.global.defaultFontFamily = 'Lato';
        Chart.defaults.global.defaultFontSize = 18;
        Chart.defaults.global.defaultFontColor = '#00000';
        let label = new Array();
        data= new Array();
        for(let i =1; i<daysInMonth(month,year)+1; i++) label.push(i);
        for(let i=1; i<daysInMonth(month,year); i++) data.push(0);
        result.forEach((value,index) => {
            data[value.ngay-1]= value.soluong
        });
        let massPopChart = new Chart(myChart, {
          type:'bar',
          data:{
            labels:label,
            datasets:[{
              label:'Bài trong ngày',
              data:data,
              backgroundColor:
                'rgba(54, 162, 235, 0.6)',
              borderWidth:1,
              borderColor:'#00000',
              hoverBorderWidth:3,
              hoverBorderColor:'#000'
            }]
          },
          options:{
            title:{
              display:true,
              text:`Số lượng bài viết trong tháng ${month} năm ${year}`,
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
              },
              ticks: {
                suggestedMin: 0,

                beginAtZero: true
            }
            }],
            xAxes: [{
                scaleLabel: {
                display: true,
                labelString: 'Ngày'
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