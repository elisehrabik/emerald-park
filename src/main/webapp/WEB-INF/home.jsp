<main>

<%--    Hero    --%>
    <div class="hero home-hero">
        <img src="${appURL}/images/404_forest.jpg" alt="Aerial view of forest" class="img-fluid forest">
        <img src="${appURL}/images/arrow-home.png" alt="Dashed arrow with x" class="img-fluid arrow-home">
        <div class="container">
            <h1>Find Your Next <br><span>Adventure.</span></h1>
        </div>

    </div>

    <div class="container marketing">
            <%--    Featurettes (Welcome, Plan your adventure, Join the community    --%>

            <%--            Welcome     --%>

                <div class="container mt-0 mb-0">
                    <div class="row justify-content-center text-center">
                        <div class="col-md-6">
                            <h2 class="featurette-heading mb-0">Welcome to Emerald Park</h2>
                            <p class="lead mt-2 mb-0">
                                Explore our gem-inspired trails, from the rugged Obsidian Trail to the serene Selenite Trail.
                                Whether you're seeking a challenge or a peaceful walk,
                                <span class="text-body-secondary">there's a path for you.</span>
                            </p>
                        </div>
                    </div>
                </div>


                <hr class="featurette-divider">

            <%--            Plan Your Adventure     --%>
        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading lh-1">Plan Your Adventure</h2>
                <p class="lead">Detailed maps, difficulty-ratings, and reviews make it easy to find what kind of trail you’re looking for. No more biting off more than you can chew. </p>
                <a class="button-dark" href="${appURL}/view-trails">View All Trails</a>
            </div>
            <div class="col-md-5 order-md-1">
                <img src="${appURL}/images/plan-your-adventure.png" alt="Person with yellow jacket walking in forest" height="500rem">
            </div>
        </div>
        <hr class="featurette-divider">

            <%--            Join the Community     --%>
        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading lh-1">Join the Community</h2>
                <p class="lead">The beauty of nature is meant to be shared. Visitors can share their experiences at Emerald Park and be informed of events.</p>
                <a class="button-dark" href="${appURL}/signup">Sign Up</a>
            </div>
            <div class="col-md-5">
                <img src="${appURL}/images/join-the-community.png" alt="Two people hiking and smiling" height="500rem">
            </div>
        </div>

        <hr class="featurette-divider">

        <!-- /END THE FEATURETTES -->

            <%--    Choose Your Adventure   --%>
                <div>
                    <h2 class="featurette-heading lh-1">Choose Your Adventure</h2>
                    <p class="lead mb-5 ">How will you explore? Click to see trails with your chosen mode of transport.</p>
            <div class="row">
                <div class="col-lg-4">
                    <a href="#" class="text-body-secondary">
                    <img src="${appURL}/images/on-foot.png" alt="Two people running on forest trail" height="300rem">
                    <h4 class="fw-normal mt-2">On Foot</h4>
                    </a>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <a href="#" class="text-body-secondary">
                    <img src="${appURL}/images/by-bike.png" alt="Person biking on forest trail" height="300rem">
                    <h4 class="fw-normal mt-2">By Bike</h4>
                    </a>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <a href="#" class="text-body-secondary">
                    <img src="${appURL}/images/by-horse.png" alt="Person riding horse on forest trail" height="300rem">
                    <h4 class="fw-normal mt-2">By Horse</h4>
                    </a>
                </div><!-- /.col-lg-4 -->
            </div><!-- /.row -->
    </div>

                <hr class="featurette-divider">

                <%-- Trails --%>
                <div class="container">
                    <h2 class="featurette-heading lh-1">Dozens of Trails at Your Fingertips.</h2>
                    <p class="lead">Emerald Park has 30+ incredible trails for you to explore.</p>

                    <div id="trailCarousel" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <c:forEach items="${trails}" var="trail" varStatus="status">
                                <c:if test="${status.index % 3 == 0}">
                                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                    <div class="row">
                                </c:if>

                                <div class="col-md-4 mb-4">
                                    <div class="card h-100">
                                        <img src="${trail.trail_image}" class="card-img-top" alt="${trail.trail_name} hiking trail at Emerald Park.">
                                        <div class="card-body">
                                            <h5 class="card-title">${trail.trail_name} Trail</h5>
                                            <p class="card-text"><small class="text-muted">${trail.trail_distance} miles | ${trail.trail_difficulty}</small></p>
                                        </div>
                                    </div>
                                </div>

                                <c:if test="${status.index % 3 == 2 || status.last}">
                                    </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>

                        <button class="carousel-control-prev" type="button" data-bs-target="#trailCarousel" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#trailCarousel" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>

                    <div class="text-center mt-4">
                        <a class="button-dark" href="${appURL}/view-trails">View All Trails</a>
                    </div>
                </div>
    </div><!-- /.container -->
</main>