<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<main>
    <h2>게시판</h2>
    <table class="board">
        <colgroup>
            <col style="width: 10%">
            <col style="">
            <col style="width: 15%">
            <col style="width: 15%">
            <col style="width: 10%">
        </colgroup>
        <tbody>
        <tr>
            <td colspan="2" class="alglft">
                <select>
                    <option>제 목</option>
                    <option>작성자</option>
                    <option>본 문</option>
                </select>
                <input type="text">
                <button type="button"
                    class="btn btn-dark">검색하기</button>
            </td>
            <!--<td></td>-->
            <td colspan="3" class="btnrgt">
                <button type="button" id="newbtn"
                    class="btn btn-success">새글쓰기</button>
            </td>
            <!--<td></td>
            <td></td>-->
        </tr>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회</th>
        </tr>

        <%-- for(Board bd : boards) --%>
        <c:forEach items="${boards}" var="bd">
            <tr>
                <td>${bd.bno}</td>
                <td><a href="/board/view?bno=${bd.bno}">${bd.title}</a></td>
                <td>${bd.userid}</td>
                <td>${fn:substring(bd.regdate, 0, 10)}</td>
                <td>${bd.views}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <ul class="pagenation">
        <c:if test="${param.cpg - 1 gt 0}">
            <li><a href="?cpg=${param.cpg - 1}">이전</a></li>
        </c:if>

        <%-- for(int i = 0; i < 10; ++i) --%>
        <c:forEach var="i" begin="1" end="10">
            <c:if test="${param.cpg ne i}">
            <li><a href="?cpg=${i}">${i}</a></li></c:if>

            <c:if test="${param.cpg eq i}">
            <li class="cpage">
                <a href="?cpg=${i}">${i}</a></li></c:if>
        </c:forEach>

        <li><a href="?cpg=${param.cpg + 1}">다음</a></li>
    </ul>
</main>
<script src="/assets/js/board.js"></script>
