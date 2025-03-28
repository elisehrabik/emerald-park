<div class="container py-4">
    <h2>Delete Trail</h2>
    <a href="trails" class="btn btn-primary long-button">View All Trails</a>
    <c:if test="${not empty trailDeletedMessage}">
        <div class="alert ${trailDeletedMessage == 'Trail deleted successfully.' ? 'alert-success' : 'alert-danger'}" role="alert">
                ${trailDeletedMessage}
        </div>
    </c:if>

    <form method="POST" action="delete-trail">
        <input type="hidden" name="trail_id" value="${trail.trail_id}" />
        <button class="btn btn-danger" type="submit">Delete</button>
    </form>
</div>
