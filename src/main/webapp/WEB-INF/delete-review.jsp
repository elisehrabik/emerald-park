<div class="container py-4">
    <div class="card transparent-overlay border rounded-3 p-5">

        <h2 class="text-light">Delete Review</h2>
        <a href="reviews" class="btn btn-primary table-button mb-5">View All Reviews</a>
        <c:if test="${not empty reviewDeletedMessage}">
            <div class="alert ${reviewDeletedMessage == 'Review deleted successfully.' ? 'alert-success' : 'alert-danger'}" role="alert">
                    ${reviewDeletedMessage}
            </div>
        </c:if>

        <form method="POST" action="${appURL}/delete-review">
            <input type="hidden" name="review_id" value="${review.review_id}" />
            <label class="mt-5 text-light fs-5">Are you sure you want to delete this review?</label> <br>
            <button class="btn btn-primary table-button delete-button mt-3" type="submit">Delete</button>
        </form>
    </div>
</div>