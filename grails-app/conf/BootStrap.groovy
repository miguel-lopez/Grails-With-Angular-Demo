import angulardemo.Product

class BootStrap {

    def init = { servletContext ->
        new Product(name: "BlackBerry Z30", category: "Mobiles", serialNumber: "AB23", price: 34400,
                description: "Document Viewer, Document Editor, Pushmail (Microsoft Exchange ActiveSync), Documents To Go, Adobe Reader, Microsoft Word, Microsoft Excel, Microsoft PowerPoint, Print To Go, eBook, Spreadsheets").save(failOnError: true, flush: true)
        new Product(name: "Lenovo S930", category: "Mobiles", serialNumber: "AM47", price: 19200,
                description: "Auto Focus, High Speed Bursts of Upto 99 In-a-row, Panoramic Scene, HDR, Intelligent Smile Correction, Multiple Angle View, Self-timer").save(failOnError: true, flush: true)
    }

    def destroy = {

    }
}
