db = db.getSiblingDB('product-service');
db.createCollection('product');


db.product.insertOne({
    name: "iPhone 13",
    description: "iPhone 13",
    price: 1200
});


db.product.insertOne({
    name: "iPhone 12",
    description: "iPhone 12",
    price: 900
});


db.product.insertOne({
    name: "iPhone 14",
    description: "iPhone 14",
    price: 1500
})
