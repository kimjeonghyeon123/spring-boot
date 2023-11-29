function validateForm() {
    var itemNameInput = document.getElementById('itemName');
    var itemName = document.getElementById('itemName').value;
    var priceInput = document.getElementById('price');
    var price = document.getElementById('price').value.trim();
    var quantityInput = document.getElementById('quantity');
    var quantity = document.getElementById('quantity').value.trim();

    if (itemName.trim() === '') {
        alert('이름을 입력해주세요!');
        itemNameInput.focus();
        return false;
    }
    if (price === '') {
        alert('가격을 입력해주세요!');
        priceInput.focus();
        return false;
    }
    if (quantity === '') {
        alert('수량을 입력해주세요!');
        quantityInput.focus();
        return false;
    }
    if (isNaN(price)) {
        alert('가격은 숫자로 입력해야 합니다!');
        priceInput.focus();
        priceInput.value = '';
        return false;
    }
    if (isNaN(quantity)) {
        alert('수량은 숫자로 입력해야 합니다!');
        quantityInput.focus();
        quantityInput.value = '';
        return false;
    }
    if (price < 1000 || price >= 1000000) {
        alert('가격은 1000원 이상 1000000원 미만으로 입력해야 합니다!');
        priceInput.focus();
        priceInput.value = '';
        return false;
    }
    if (quantity < 10 || quantity >= 1000) {
        alert('수량은 10개 이상 1000개 미만으로 입력해야 합니다!');
        quantityInput.focus();
        quantityInput.value = '';
        return false;
    }

    return true;
}