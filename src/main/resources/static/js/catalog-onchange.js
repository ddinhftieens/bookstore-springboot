function catalog() {
    var query = document.getElementById("catagory").value;
    window.location.href = '/home/catalog?query=' + query;
}