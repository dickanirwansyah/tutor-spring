new Vue({
    el: '#app',
    data: {
        barangs: [],
        cart: {
            email: '',
            address: '',
            items: []
        },
        searchOrderId: '',
        order: {},
        orderStatus: ''
    },

    created: function(){
        this.fetchProducts()
    },

    /** methods **/
    methods: {
        fetchProducts: function(){
            $.ajax({
                url: 'http://localhost:8080/api/v1/barang'
            }).done(function(data){
                this.barangs = data
                console.log(data)
            }.bind(this));
        },

        addToCart: function(barang){
            console.log('addToCart', this.cart);
            var added = false;
            this.cart.items.forEach(function(b){
                if(b.productId == barang.barangId){
                   b.qty = b.qty + 1;
                   added = true
                   return;
                }
            });
            if(added) return;

            this.cart.items.push({
                productId: barang.barangId,
                qty: 1,
                productPrice: barang.price
            });
            console.log('Cart : ', this.cart)
        },

        placeOrder: function(){
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/api/v1/order',
                contentType: 'application/json',
                data: JSON.stringify(this.cart)
            }).done(function(data){
                this.cart = {
                    email: '',
                    address: '',
                    items: []
                };
                this.orderStatus = "Order created successfully : "+data.id
            }.bind(this))
        },

        searchOrder: function(){
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/api/v1/order/'+this.searchOrderId,
                contentType: 'application/json'
            }).done(function(data){
                this.order = data;
            }.bind(this))
        }
    },

    computed: {

    }
});