$(document).ready(function() {
  $("#create").click(function() {
    let code = $("#code").val();
    let region = $("#region").val();
    let lendingType = $("#lendingType").val();
    let incomeLevel = $("#incomeLevel").val();
    let url = "http://localhost:8080/api/countries";

    if (!code) alert("Country code can not be empty");
    else {
      let requestParam = "?code=" + code;
      if (region) {
        requestParam += "&region=" + region;
      }
      if (lendingType) {
        requestParam += "&lendingType=" + lendingType;
      }
      if (incomeLevel) {
        requestParam += "&incomeLevel=" + incomeLevel;
      }
      $.get(url + requestParam, function(data, status) {
        let table = $("#output");
        table.empty();
        $.each(data, function(index, country) {
          table.append(
            "<tr><td>" +
              country.name +
              "</td> <td>" +
              country.capitalCity +
              "</td> </tr>"
          );
        });
      });
    }
  });
});
