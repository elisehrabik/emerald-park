
<div class="container py-4">
    <h2>Admin - All Reviews</h2>
    <div class="table-responsive small">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col" style="width: 120px;">Edit/Delete</th>
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
                    <td style="width: 120px;">
                        <a href="edit-review?review_id=${review.review_id}" class="btn btn-sm btn-outline-primary">Edit</a>
                        <a href="delete-review?review_id=${review.review_id}" class="btn btn-sm btn-outline-danger">Delete</a>
                    </td>

                    <td>${review.trail_name}</td>
                    <td>${review.email}</td>
                    <td>${review.review_date}</td>
                    <td>${review.rating}</td>
                    <td>${review.review_time}</td>
                    <td style="width: 300px;">${review.review_notes}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
