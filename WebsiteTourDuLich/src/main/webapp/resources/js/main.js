/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function  themVaoGio(tourId, tenTour, gia, soCho) {
    event.preventDefault();
    fetch("/WebTourDuLich/api/gioHang", {
        method: 'post',
        body: JSON.stringify({
            "tourId": tourId,
            "tenTour": tenTour,
            "gia": gia,
            "soLuong": 1,
            "soCho": soCho
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json()
    }).then(function (data) {
        let d = document.getElementById("slTour");
        d.innerText = data;
    })
}


function xoaTour(tourId) {
    event.preventDefault();
    if (confirm("Bạn có chắc chắn xóa tour này không?") == true) {
        fetch(`/WebTourDuLich/api/themSuaTour/${tourId}`, {
            method: "delete",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status == 200) {//thanh cong
                let d = document.getElementById(`pro${tourId}`);
                d.style.display = "none";
            } else
                alert("Đã có lỗi xảy ra!!!");
        })
    }
}
function xoaNguoiDung(id) {
    event.preventDefault();
    if (confirm("Bạn có chắc chắn xóa người dùng này không?") == true) {
        fetch(`/WebTourDuLich/api/nguoiDungs/${id}`, {
            method: "delete",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status == 200) {//thanh cong
                let d = document.getElementById(`n${id}`);
                d.style.display = "none";
                location.reload();
            } else
                alert("Đã có lỗi xảy ra!!!");
        })
    }
}

function  capNhatSLTour(obj, tourId, soCho) {
    event.preventDefault();
    if (obj.value <= soCho) {
        fetch("/WebTourDuLich/api/gioHang", {
            method: "put",
            body: JSON.stringify({
                "tourId": tourId,
                "tenTour": "",
                "gia": 0,
                "soLuong": obj.value,
                "soCho": 0
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (res) {
            return res.json()
        }).then(function (data) {
            let slTour = document.getElementById("slTour");
            slTour.innerText = data.slTour;
            let tongTien = document.getElementById("tongTien");
            tongTien.innerText = data.tongTien;

            var formatter = new Intl.NumberFormat('vi', {
                style: 'currency',
                currency: 'VND',
            });
            tongTien.innerText = formatter.format(tongTien.innerText);
            location.reload();
        })
    } else {
        fetch("/WebTourDuLich/api/gioHang", {
            method: "put",
            body: JSON.stringify({
                "tourId": tourId,
                "tenTour": "",
                "gia": 0,
                "soLuong": soCho,
                "soCho": 0
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (res) {
            return res.json()
        }).then(function (data) {
            let slTour = document.getElementById("slTour");
            slTour.innerText = data.slTour;
            let tongTien = document.getElementById("tongTien");
            tongTien.innerText = data.tongTien;
            let soLuong = document.getElementById("soLuong");
            soLuong.innerText = soCho;
            location.reload();
        })
    }
}
function xoaTourTrongGio(tourId) {
    event.preventDefault();
    if (confirm("Bạn có chắc chắn xóa tour này không?") == true) {
        fetch(`/WebTourDuLich/api/gioHang/${tourId}`, {
            method: "delete",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (res) {
            return res.json()
        }).then(function (data) {
            let slTour = document.getElementById("slTour");
            slTour.innerText = data.slTour;
            let tongTien = document.getElementById("tongTien");
            tongTien.innerText = data.tongTien;
            location.reload();
        })
    }
}
function thanhToan(id, soLuongTour, online, thanhToanOnline) {
    event.preventDefault();
    if (soLuongTour != 0) {
        if (online == true) {
            let page = "/WebTourDuLich/paypal?tongTien=" + thanhToanOnline;
            window.location = page;
            if (confirm("Tiến hành thanh toán!!!") == true) {
                fetch(`/WebTourDuLich/api/thanhToanOnline/${id}`, {
                    method: "post",
                    headers: {
                        "Content-Type": "application/json"
                    }
                }).then(function (res) {
                    return res.json()
                }).then(function (code) {
                    console.info(code);
                    location.reload();
                })
                alert("Bạn đã đăng kí thành công");
            }
        } else {

            if (confirm("Tiến hành thanh toán!!!") == true) {
                fetch(`/WebTourDuLich/api/thanhToan/${id}`, {
                    method: "post",
                    headers: {
                        "Content-Type": "application/json"
                    }
                }).then(function (res) {
                    return res.json()
                }).then(function (code) {
                    console.info(code);
                    location.reload();
                    window.location = "/WebTourDuLich/dsTour";
                })
                alert("Bạn đã đăng kí thành công");
                
            }
        }
    } else
        alert("Bạn chưa đặt tour nào!!!");

}

function themBinhLuan(tourId, id) {
    event.preventDefault();

    fetch(`/WebTourDuLich/api/themBinhLuan/${id}`, {
        method: 'post',
        body: JSON.stringify({
            "noiDung": document.getElementById("binhLuanId").value,
            "tourId": tourId
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        console.info(res)
        return res.json();
    }).then(function (data) {
        console.info(data)
        let area = document.getElementById("binhLuanArea");
        area.innerHTML = `
                    <div class="row">
                        <div class="col-md-2 d-flex justify-content-end">
                            <img class="img-fluid rounded-circle" alt="alt"/>
                        </div>
                        <div class="col-md-10">
                            <p>${data.noiDung}</p>
                            <i class="ngayBL">${moment(data.ngayBL).fromNow()}</i>
                        </div>
                    </div>
                    ` + area.innerHTML;
        location.reload();
    })
}
window.onload = function () {
    let gia = document.getElementsByClassName("giaTien")
    let ngays = document.getElementsByClassName("ngayBL")
    let ngayThangNam = document.getElementsByClassName("ngay-thang-nam")

    //dau phay trong tien
    var formatter = new Intl.NumberFormat('vi', {
        style: 'currency',
        currency: 'VND',
    });
    for (let i = 0; i < gia.length; i++)
    {

        gia[i].innerText = formatter.format(gia[i].innerText);
    }

    //thoi gian binh luan
    for (let i = 0; i < ngays.length; i++)
    {
        ngays[i].innerText = "Bình luận " + moment(ngays[i].innerText).fromNow();
    }

    //thoi gian 
    for (let i = 0; i < ngayThangNam.length; i++)
    {
        ngayThangNam[i].innerText = moment(ngayThangNam[i].innerText).format('ll');
    }

    AOS.init();
}

$('body').scrollspy({target: '#navbar-example'})
