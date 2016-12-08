function init(){

	completeFeild = document.getElementById("searchId");
	completeTable = document.getElementById("complete-table");
	autoRow = document.getElementById("autocompleteContainer");
}

function doCompletion(){

	var url = "AutocompleteServlet?action=complete&searchId=" + encodeURI(searchId.value);
	var xhttp;
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
		isIE = false;
	} else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		isIE = true;
	}

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			callback(this);
		}
	};
	xhttp.open("GET", url, true);
	xhttp.send();
}

function callback(xhttp){
	clearTable();
	parseMessage(xhttp.responseXML);
}

function clearTable(){
	if(completeTable.getElementsByTagName("tr").length > 0){
		completeTable.style.display = 'none';
		for(loop = completeTable.childNodes.length - 1; loop >= 0; loop--){
			completeTable.removeChild(completeTable.childNodes[loop])
		}
	}
}

function parseMessage(responseXML){
	
	if(responseXML == null){
		return false;
	}else{
		console.log(responseXML);
		var products = responseXML.getElementsByTagName("products")[0];
		if(products.childNodes.length>0){
			completeTable.setAttribute("bordercolor", "black");
			completeTable.setAttribute("border", "1");
			
			for(loop = 0; loop<products.childNodes.length; loop++){
				var product = products.childNodes[loop];
				var prodName = product.getElementsByTagName("productName")[0];
				var prodId = product.getElementsByTagName("id")[0];
				appendProduct(prodName.childNodes[0].nodeValue, prodId.childNodes[0].nodeValue);
			}
		}
	}
}

function appendProduct(prodName, prodId){

	var row;
	var cell;
	var linkElement;
	
	if(isIE){
		completeTable.style.display = 'block';
		row = completeTable.insertRow(completeTable.rows.length);
		cell = row.insertCell(0);
	}else{
		completeTable.style.display='table';
		row=document.createElement("tr");
		cell=document.createElement("td");
		row.appendChild(cell);
		completeTable.appendChild(row);
	}
	
	cell.className = "popupCell";
	linkElement = document.createElement("a");
	linkElement.className = "popupItem";
	linkElement.setAttribute("href", "AutocompleteServlet?action=lookup&searchId=" + prodId);
	linkElement.setAttribute("style", "color: #1E90FF; display: block; font-family: 'Pontano Sans',Arial,sans-serif; text-decoration: none;");
	linkElement.appendChild(document.createTextNode(prodName));
	cell.appendChild(linkElement);
}













