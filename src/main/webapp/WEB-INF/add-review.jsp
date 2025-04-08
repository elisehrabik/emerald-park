
<div class="container py-4">
    <div class="card transparent-overlay border rounded-3 p-5">
        <h2 class="text-light">Add Review</h2>
        <a href="${appURL}/view-trail?id=${trailId}" class="table-button mb-5 text-center">Back to Trail</a>

        <form method="POST" action="${appURL}/add-review">
            <input type="hidden" name="trail_id" value="${trailId}">
            <input type="hidden" name="user_id" value="${user.userId}">

            <p  class="form-label text-light">Rating:</p>
            <div class="star-rating mb-1">
                <c:forEach var="i" begin="1" end="5">
                    <c:set var="star" value="${6 - i}" />
                    <input type="radio" id="star${star}" name="rating" value="${star}"
                           <c:if test="${rating == star}">checked</c:if>>
                    <label for="star${star}" title="${star} stars">&#9733;</label>
                </c:forEach>
            </div>

            <c:if test="${ratingError}">
                <div class="text-danger mb-3">${ratingMessage}</div>
            </c:if>


            <div class="mb-3">
                <label for="review_notes" class="form-label text-light">Your Review:</label>
                <textarea class="form-control" id="review_notes" name="review_notes" rows="4"></textarea>
            </div>

            <button class="table-button" type="submit">Submit Review</button>
        </form>
    </div>
</div>

