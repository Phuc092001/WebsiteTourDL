<%-- 
    Document   : thanhToan
    Created on : Aug 9, 2023, 2:54:25 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<div class="container" align="center">
    <h1>Check Out</h1>
    <br/>
    <form:form action="authorize_payment" method="POST">
        <table class="table ">
            <tr>
                <td class="text-center">Sản phẩm: </td>
                <td><input type="text" name="product" value="Tour du lịch" readonly/></td>
            </tr>
            <tr>
                <td class="text-center">Tổng tiền(VND): </td>
                <td><span class="giaTien"/>${tongTien}</span>
            </tr>
            <tr>
                <td class="text-center">Tổng tiền(USD): </td>    
                <td><input type="text" name="subtotal" value=${tongTien/23800} readonly/></td>
            </tr>
            <tr style="display:none;">
                <td class="text-center">Shipping:</td>
                <td><input type="text" name="shipping" value="0.0" readonly/></td>
            </tr>    
            <tr>
                <td class="text-center">Thuế: </td>
                <td><input type="text" name="tax" value="0.0" readonly/></td>
            </tr>    
            <tr>
                <td class="font-weight-bold text-center">Tổng tiền phải trả(USD): </td>
                <td><input type="text" name="total" value=${tongTien/23800} readonly/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input class="btn btn-info thanh-toan" type="submit" value="Checkout"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>

