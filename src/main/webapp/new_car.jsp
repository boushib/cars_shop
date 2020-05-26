<%@include file="header.jsp"%>
<main class="new-car center-content">
  <div class="container">
    <div class="form-box">
      <h2>Add a new car</h2>
      <form action="insert_car.do" method="post">
        <div class="form-control">
          <label for="make">Make</label>
          <input type="text" name="make" id="make" placeholder="Enter car make">
        </div>
        <div class="form-control">
          <label for="model">Model</label>
          <input type="text" name="model" id="model" placeholder="Enter car model">
        </div>
        <div class="form-control">
          <label for="year">Year</label>
          <input type="number" name="year" id="year" placeholder="Enter car year">
        </div>
        <div class="form-control">
          <label for="price">Price in USD</label>
          <input type="number" name="price" id="price" step="100" placeholder="Enter car price">
        </div>
        <div class="form-control">
          <label for="country">County</label>
          <input type="text" name="country" id="country" placeholder="Enter country">
        </div>
        <div class="form-control">
          <label for="city">City</label>
          <input type="text" name="city" id="city" placeholder="Enter city">
        </div>
        <button class="btn">Add new Car</button>
      </form>
    </div>
  </div>
</main>
<%@include file="footer.jsp"%>