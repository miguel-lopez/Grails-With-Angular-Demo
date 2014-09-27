package angulardemo

import grails.converters.JSON

class Product {

    String name
    String category
    String serialNumber
    BigDecimal price
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false
        category nullable: true
        serialNumber nullable: false, blank: false
        price nullable: false
        description nullable: true

        dateCreated nullable: true
        lastUpdated nullable: true
    }

    static mapping = {
        description type: 'text'
    }

    def toJson() {
        return [name: this.name,
                category: this.category,
                serialNumber: this.serialNumber,
                price: this.price,
                description: this.description,
                dateCreated: this.dateCreated,
                lastUpdated: this.lastUpdated,
                id: this.id] as JSON
    }
}
