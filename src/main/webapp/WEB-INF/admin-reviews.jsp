<div class="container py-4">
    <div class="row">
        <!-- Main content START -->
        <div class="col-xl-12">
            <!-- Title -->
            <h1>All Reviews</h1>
            <p class="lead admin-result">
                <c:choose>
                    <c:when test="${reviews.size() == 1}">There is 1 review</c:when>
                    <c:otherwise>There are ${reviews.size()} reviews</c:otherwise>
                </c:choose>
            </p>
            <div class="text-center mb-5">
                <a href="admin-add-review" class="btn btn-primary table-button">Add Review</a>
            </div>
            <c:if test="${reviews.size() > 0}">
                <div class="table-responsive my-table" style="padding:0; border-radius: 5px 5px 5px 5px;">
                    <table class="table table-bordered my-table">
            <thead>
            <tr>
                <th scope="col" style="width: 120px;"></th>
                <th scope="col">Trail Name</th>
                <th scope="col">Visitor Email</th>
                <th scope="col">Date</th>
                <th scope="col">Rating</th>
                <th scope="col">Time to Complete</th>
                <th scope="col" style="width: 200px;">Notes</th>
            </thead>
            <tbody>
            <c:forEach items="${reviews}" var="review">
                <tr>
                    <td>
                        <a href="edit-review?review_id=${review.review_id}" class="btn btn-sm btn-outline-primary mb-1 edit-color" style="width:100%">Edit</a>
                        <a href="delete-review?review_id=${review.review_id}" class="btn btn-sm btn-outline-danger delete-color" style="width:100%">Delete</a>
                    </td>

                    <td>${review.trail_name}</td>
                    <td>${review.email}</td>
                    <td>${review.review_date}</td>
                    <td>${review.rating}</td>
                    <td>${review.review_time}</td>
                    <td style="width: 300px;">${review.review_notes}</td>
            </c:forEach>
            </tbody>
        </table>
    </div>
            </c:if>
</div>
    </div>
</div>

</body>
</html>
