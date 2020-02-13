
<jsp:include page="topheader.jsp"></jsp:include>

<%if( request.getSession().getAttribute("user") == null ){%>
<div class="reglayout">
	<section class="regtext">

		<h1 align="center">Diventa un utente del nostro network</h1>

		<h3>Perchè registrarsi? Quali sono i vantaggi?</h3>
		<br> Registrandoti a Maggico, potrai usufruire di tutti i servizi
		aggiuntivi che i semplici visitatori non hanno, quali:

		<ul class="advantages">
			<li>Possibilità di assistenza immediata da un tecnico</li>
			<li>Possibilità di acquistare i prodotti presenti sul sito</li>
			<li>Spedizione in 24h (Italia)</li>
			<li>Reso gratuito entro 2 settimane, soddisfatti o rimborsati</li>
			<li>Affidabilità e professionalità da parte dei numeri 1 del settore</li>
		</ul>


		Completa questo form per registrarti:
	</section>

	<form id="registrationForm" action="RegistrationServlet?action=client"
		method="post" onsubmit="event.preventDefault(); validate(this)">
		<label for="email">Inserisci la tua e-mail:</label> <br> <input
			type="text" name="email" placeholder="latuaemail@email.com"
			title="Il campo non è stato compilato correttamente! es: latuaemail@email.com"
			required> <br> <label for="rusername">Scegli un
			username:</label> <br> <input type="text" id="rusername"
			name="rusername" placeholder="Username" required> <label
			id="invalidUser"></label><br> <label for="rpassword">Imposta
			la tua password (Necessariamente 8 caratteri, una lettera maiuscola e
			una minuscola):</label> <br> <input type="password" name="rpassword"
			title="La password deve essere di almeno 8 caratteri, deve contenere almeno un numero, una lettera maiuscola ed una minuscola"
			placeholder="Password" required> <br> <label for="iban">Inserisci
			il tuo iban:</label> <br> <input type="text" name="iban" maxlength="27"
			placeholder="Iban"
			title="Il formato accettato è quello italiano! es: IT XX X XXXXXXXXXXXXXXXXXXXXXX"
			required> <br> <br>

		<button class="regbutton" type="submit">Registrati</button>
		<br> <br> <br>

	</form>
</div>

<% } else if( (boolean) request.getSession().getAttribute("isAdmin"))  { %>

<div class="reglayout">
	<section class="regtext">

		<h1 align="center">Nomina un nuovo admin</h1>

		<p>Nominando un nuovo admin egli sarà capace di apportare
			modifiche al catalogo e ai prodotti. Fallo solo se sei sicuro.</p>

		<br> Completa questo form per registrarti:
	</section>

	<form id="registrationForm" action="AggiungiNuovoAdminServlet?action=admin"
		method="post" onsubmit="event.preventDefault(); validateA(this)">
		<label for="email">Inserisci la tua e-mail:</label> <br> <input
			type="text" name="email" placeholder="latuaemail@email.com"
			title="Il campo non è stato compilato correttamente! es: latuaemail@email.com"
			required> <br> <label for="rusername">Scegli un
			username:</label> <br> <input type="text" id="rusername"
			name="rusername" placeholder="Username" required> <label
			id="invalidUser"></label><br> <label for="rpassword">Imposta
			la tua password (Necessariamente 8 caratteri, una lettera maiuscola e
			una minuscola):</label> <br> <input type="password" name="rpassword"
			title="La password deve essere di almeno 8 caratteri, deve contenere almeno un numero, una lettera maiuscola ed una minuscola"
			placeholder="Password" required> <br>

		<button class="regbutton" type="submit">Registra</button>
		<br> <br> <br>

	</form>

</div>






<%} else { %>
<jsp:include page="exception.jsp"></jsp:include>
<%} %>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>