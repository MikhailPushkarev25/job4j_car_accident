<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <form action="<c:url value='/save'/>" method='POST'>
        <table>
            <div class="mb-3">
                <label for="name" class="form-label">Имя нарушителя</label>
                <input type="text" name="name" class="form-control" id="name" placeholder="Введите имя нарушителя">
            </div>
            <div class="mb-3">
                <label for="texts" class="form-label">Описание нарушителя</label>
                <input type="text" name="text" class="form-control" id="texts" placeholder="Опишите нарушение">
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Адрес нарушителя</label>
                <input type="text" name="address" class="form-control" id="address" placeholder="Укажите адрес">
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="type.id">Тип происшествия</label>
                    <select class="form-control" name="type.id" id="type.id">
                        <c:forEach var="type" items="${types}" >
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="col">
                <div class="form-group">
                    <label for="rIds">Статьи</label>
                    <select class="form-control" name="rIds" id="rIds">
                        <c:forEach var="rule" items="${rules}" >
                            <option value="${rule.id}">${rule.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <button class="btn btn-success">Сохранить</button>
        </table>
    </form>
</body>
</html>