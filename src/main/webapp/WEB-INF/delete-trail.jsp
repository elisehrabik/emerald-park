<div class="container py-4">
    <div class="card transparent-overlay border rounded-3 p-5">

    <h2 class="text-light">Delete Trail</h2>
    <a href="trails" class="btn btn-primary table-button mb-5">View All Trails</a>
    <c:if test="${not empty trailDeletedMessage}">
        <div class="alert ${trailDeletedMessage == 'Trail deleted successfully.' ? 'alert-success' : 'alert-danger'}" role="alert">
                ${trailDeletedMessage}
        </div>
    </c:if>

    <form method="POST" action="${appURL}/delete-trail">
        <input type="hidden" name="trail_id" value="${trail.trail_id}" />
        <label class="mt-5 text-light fs-5">Are you sure you want to delete ${trail.trail_name} trail?</label> <br>
        <button class="btn btn-primary table-button delete-button mt-3" type="submit">Delete</button>
    </form>
</div>
</div>