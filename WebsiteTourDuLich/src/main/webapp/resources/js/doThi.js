/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function randColor() {

    return 'hsla(' + (Math.random() * 360) + ', 80%, 50%, 0.5)';
}

function bieuDoTour(id, tourLabels = [], tourInfor = []) {
    let colors = [];
    for (let i = 0; i < tourLabels.length; i++)
        colors.push(randColor());
    const data = {
        labels: tourLabels,
        datasets: [{
                label: 'Biểu đồ thống kê doanh thu theo tour',
                lineTension: 0.4,
                backgroundColor: colors,
                borderColor: colors,
                data: tourInfor,
                fill: true,
                pointBackgroundColor: 'rgb(0, 24, 255)',
            }]
    };
    
    const config = {
        type: 'line',
        data: data
    };

    let bieuDo = document.getElementById(id).getContext("2d");
    
    new Chart(bieuDo, config)
    
}

function bieuDoSoLuongTour(id, tourLabels = [], tourInfor = []) {
    let colors = [];
    for (let i = 0; i < tourLabels.length; i++)
        colors.push(randColor());
    const data = {
        labels: tourLabels,
        datasets: [{
                label: 'Biểu đồ thống kê số lượng tour',
                data: tourInfor,
                backgroundColor: colors,
                hoverOffset: 30,
                cutout: 55,  
            }]
    };
    
    const config = {
        type: 'doughnut',
        data: data
    };

    let bieuDo = document.getElementById(id).getContext("2d");
    
    new Chart(bieuDo, config)
    
}
