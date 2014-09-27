modules = {
    application {
        resource url: 'js/application.js'
    }

    jquery210 {
        resource url: '/js/jquery-2.1.0.min.js'
    }

    angular {
        resource url: "js/angular.min.js", disposition: 'head'
        resource url: "js/angular-route.js"
    }

    controller {
        resource url: 'js/controller.js'
    }

    bootstrap {
        resource url: 'css/bootstrap.css'
        resource url: 'css/bootstrap-theme.css'
        resource url: 'js/bootstrap.js'
    }
}