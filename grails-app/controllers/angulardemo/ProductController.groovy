package angulardemo

import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.NO_CONTENT

@Transactional(readOnly = true)
class ProductController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render Product.list().collect { it.toJson() }
    }

    def show(Product productInstance) {
        render productInstance.toJson()
    }

    @Transactional
    def save(Product productInstance) {
        if (productInstance.hasErrors()) {
            List errors = productInstance.errors.allErrors.collect {
                message(error: it, encodeAs: 'HTML')
            }
            Map result = [status: 'error', message: errors.join('\n')]
            render result as JSON
            return
        }

        productInstance.save flush: true
        Map result = [status: 'success', id: productInstance.id]
        render result as JSON
    }

    @Transactional
    def delete(Product productInstance) {
        productInstance.delete flush: true
        render status: NO_CONTENT
    }
}
