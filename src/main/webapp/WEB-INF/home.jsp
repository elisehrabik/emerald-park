<main>

<%--    Hero    --%>
    <div class="hero home-hero">
        <img src="${appURL}/images/404_forest.jpg" alt="Aerial view of forest" class="img-fluid forest">
        <img src="${appURL}/images/arrow-home.png" alt="Dashed arrow with x" class="img-fluid d-none d-sm-block arrow-home">
        <img src="${appURL}/images/arrow-home-mobile.png" alt="Dashed arrow with x" class="img-fluid d-sm-none arrow-home">
        <div class="container">
            <h1>Find Your Next <br><span>Adventure.</span></h1>
        </div>

    </div>

    <div class="container marketing">
            <%--    Featurettes (Welcome, Plan your adventure, Join the community    --%>

            <%--            Welcome     --%>

                <div class="container mt-5 mt-lg-3 mb-3 fade-up">
                    <div class="row justify-content-center text-center">
                        <div class="col-12 col-md-10 col-lg-8">
                            <h2 class="featurette-heading mb-3">Welcome to Emerald Park</h2>
                            <p class="lead mb-0">
                                Explore our gem-inspired trails, from the rugged Obsidian Trail to the serene Selenite Trail.
                                Whether you're seeking a challenge or a peaceful walk,
                                <span class="text-body-secondary">there's a path for you.</span>
                            </p>
                        </div>
                    </div>
                </div>

                <hr class="featurette-divider">

            <%--            Plan Your Adventure     --%>
                <div class="row featurette justify-content-center fade-up">
                    <div class="col-12 col-lg-7 order-lg-2 d-flex flex-column justify-content-center">
                        <div class="mx-lg-auto px-lg-4" style="max-width: 600px;">
                            <h2 class="featurette-heading lh-1 mb-3">Plan Your Adventure</h2>
                            <p class="lead">
                                Detailed maps, difficulty-ratings, and reviews make it easy to find what kind of trail you’re looking for.
                            </p>
                            <a class="button-dark d-flex justify-content-center align-items-center mx-auto mx-lg-0 mb-5" href="${appURL}/map">See Map</a>
                        </div>
                    </div>

                    <div class="col-12 col-lg-5 order-lg-1 d-flex justify-content-center">
                        <img src="${appURL}/images/plan-your-adventure.png" alt="Person with yellow jacket walking in forest"
                             class="img-fluid rounded custom-shadow">
                    </div>
                </div>


                <hr class="featurette-divider">


            <%--            Join the Community     --%>
                <div class="row featurette justify-content-between fade-up">
                    <div class="col-12 col-lg-7 order-lg-1 d-flex flex-column justify-content-center">
                        <div class="mx-lg-auto px-lg-4" style="max-width: 600px;">
                            <h2 class="featurette-heading lh-1 mb-3">Join the Community</h2>
                            <p class="lead">
                                The beauty of nature is meant to be shared. Visitors can share their experiences at Emerald Park and be informed of events.
                            </p>
                            <a class="button-dark d-flex justify-content-center align-items-center mx-auto mx-lg-0 mb-5" href="${appURL}/signup">Sign Up</a>
                        </div>
                    </div>

                    <div class="col-12 col-lg-5 order-lg-2 d-flex justify-content-center">
                        <img src="${appURL}/images/join-the-community.png" alt="Two people hiking and smiling"
                             class="img-fluid rounded custom-shadow">
                    </div>
                </div>


                <hr class="featurette-divider">


        <!-- /END THE FEATURETTES -->

            <%--    Choose Your Adventure   --%>
                <div class="fade-up">
                    <h2 class="featurette-heading lh-1 mb-3">Choose Your Adventure</h2>
                    <p class="lead mb-5 ">How will you explore? Click to see trails with your chosen mode of transport.</p>
            <div class="row">
                <div class="col-lg-4">
                    <a href="${appURL}/view-trails?categories=1" class="text-body-secondary">
                    <img src="${appURL}/images/on-foot.png" class="hover-no-shadow-left" alt="Two people running on forest trail" height="300rem">
                    <h4 class="fw-normal mt-2">On Foot</h4>
                    </a>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <a href="${appURL}/view-trails?categories=2" class="text-body-secondary">
                    <img src="${appURL}/images/by-bike.png" class="hover-no-shadow-right" alt="Person biking on forest trail" height="300rem">
                    <h4 class="fw-normal mt-2">By Bike</h4>
                    </a>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <a href="${appURL}/view-trails?categories=3" class="text-body-secondary">
                    <img src="${appURL}/images/by-horse.png" class="hover-no-shadow-left" alt="Person riding horse on forest trail" height="300rem">
                    <h4 class="fw-normal mt-2">By Horse</h4>
                    </a>
                </div><!-- /.col-lg-4 -->
            </div><!-- /.row -->
    </div>
                <hr class="featurette-divider">

                <%-- Trails --%>
                <div class="container fade-up">
                    <h2 class="featurette-heading lh-1 mb-3">Dozens of Trails at Your Fingertips.</h2>
                    <p class="lead">Emerald Park has 30+ incredible trails for you to explore.</p>

                    <%-- Desktop Carousel: 3 per slide --%>
                    <div id="trailCarouselDesktop" class="carousel slide d-none d-md-block" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <c:forEach items="${trails}" var="trail" varStatus="status">
                                <c:if test="${status.index % 3 == 0}">
                                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                    <div class="row">
                                </c:if>

                                <div class="col-md-4 mb-4">

                                        <div class="card h-100 custom-shadow">
                                            <img src="${trail.trail_image}" class="card-img-top" alt="${trail.trail_name}">
                                            <div class="card-body">
                                                <a href="view-trail?id=${trail.trail_id}">
                                                    <h5 class="card-title">${trail.trail_name} Trail</h5>
                                                </a>
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
                        <button class="carousel-control-prev" type="button" data-bs-target="#trailCarouselDesktop" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#trailCarouselDesktop" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>

                    <%-- Mobile Carousel: 1 per slide --%>
                    <div id="trailCarouselMobile" class="carousel slide d-block d-md-none" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <c:forEach items="${trails}" var="trail" varStatus="status">
                                <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                    <a href="view-trail?id=${trail.trail_id}">
                                        <div class="card custom-shadow mx-2">
                                            <img src="${trail.trail_image}" class="card-img-top" alt="${trail.trail_name}">
                                            <div class="card-body">
                                                <h5 class="card-title">${trail.trail_name} Trail</h5>
                                                <p class="card-text"><small class="text-muted">${trail.trail_distance} miles | ${trail.trail_difficulty}</small></p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#trailCarouselMobile" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#trailCarouselMobile" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>


                    <br>
                    <div class="text-center mt-4 d-flex justify-content-center" style="align-items: center;">
                        <a class="button-dark" href="${appURL}/view-trails">View All Trails</a>
                    </div>
                </div>
    </div><!-- /.container -->
</main>


<script src="${appURL}/scripts/fade-up-animation.js"></script>

