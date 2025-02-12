

<link rel="stylesheet" href="${appURL}/styles/404.css"/>
<body>
<div id="error-page">
    <div>
        <div>
            <div>
                <img src="${appURL}/images/404_forest.jpg" alt="Aerial view of forest" class="img-fluid">
                <c:choose>
                    <c:when test="${initParam['debugging'] eq 'true'}">
                        <p id="error-p">${errorMsg}</p>
                    </c:when>
                    <c:otherwise>
                        <h1>Error</h1>
                        <h2>Uh oh!</h2>
                        <p  class="mb-4">Something went wrong. We are sorry for the inconvenience.</p>
                        <div class="d-flex justify-content-center">
                            <a href="${appURL}" class="btn btn-primary">Home</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>