<%@include file="header.jsp"%>
<main class="all-cars">
  <div class="container">
    <h1>
      All cars
      <a href="new_car.do"><span class="material-icons add">add_circle</span></a>
    </h1>
    <table>
      <thead>
      <tr>
        <th>id</th>
        <th>make</th>
        <th>model</th>
        <th>year</th>
        <th>price</th>
        <th>country</th>
        <th>city</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach items="${cars}" var="car">
          <tr>
            <td><c:out value="${ car.id }" /></td>
            <td><c:out value="${ car.make }" /></td>
            <td><c:out value="${ car.model }" /></td>
            <td><c:out value="${ car.year }" /></td>
            <td>$<c:out value="${ car.price }" /></td>
            <td><c:out value="${ car.country }" /></td>
            <td><c:out value="${ car.city }" /></td>
            <td class="actions">
              <form action="update_car_p.do" method="post">
                <input type="hidden" name="car_id" value="${car.id}">
                <button><span class="material-icons edit">create</span></button>
              </form>
              <form action="delete.do" method="post">
                <input type="hidden" name="car_id" value="${car.id}">
                <button><span class="material-icons delete">delete</span></button>
              </form>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</main>
<%@include file="footer.jsp"%>
