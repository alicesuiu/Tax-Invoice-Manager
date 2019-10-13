Nume: Suiu Alice-Florenta
Grupa: 323CC
Timpul alocat temei: o saptamana
Gradul de dificultate al temei: mediu

Detalii legate de implementare:
Task 1: 
	Clase:
	* Produs: pe langa datele si metodele deja cerute am suprascris metoda 
	boolean equals(Object o) pentru a verifica daca doua produse sunt 
	egale.

	* MediumMarket: am creat o metoda getTotalCategorieCuTaxe(String) care 
	calculeaza total facturi cu taxe pe magazin, pentru fiecare categorie. Am 
	lasat variabila 'tip' in clasa fiecarui tip de magazin si am creat o 
	metoda publica care returneaza acest tip.

	* MarketFactory: cu ajutorul acestei clase am folosit Factory pattern, 
	astfel incat pentru fiecare magazin se va crea un obiect corespunzator de  
	tip MiniMarket, MediumMarket sau HyperMarket, in functie de tipul 
	magazinului.

	* Gestiune: implementata folosind Singleton pattern. Am creat o functie 
	pentru citirea produselor din fisierul "produse.txt", o functie pentru 
	citirea taxelor din fisierul "taxe.txt", pentru taxe am folosit ca 
	dictionar TreeMap pentru ca elementele din arbore sa fie ordonate dupa 
	tara si o functie de citire a magazinelor din fisierul "facturi.txt". 
	Exista in aceasta clasa doua functii getInstance() pentru ca una primeste 
	ca parametrii numele fisierelor pentru crearea datelor si cea de-a doua 
	functie nu primeste parametrii, insa fisierele sunt transmise prin pagina 
	grafica unde utilizatorul incarca fisierele. Desi exista doua functii de 
	acest tip intotdeaua va fi o singura instantiere a clasei Gestiune. De 
	asemenea, am mai creat o functie "updateFiles(String, String, String)" 
	prin intermediul careia se pot adapta fisierele folosite la crearea 
	gestiunii.

	* GenerateOutput: am creat o metoda care afiseaza in fisierul "out.txt" 
	tot ce s-a cerut la task-ul 1. Am creat trei liste una pentru fiecare tip 
	de magazin. Dupa ce au fost adaugate magazinele in lista corespunzatoare 
	au fost sortate toate cele trei liste in ordinea crescatoare a totalului 
	fara taxe pe magazin. Functia de afisare in fisier este apelata de trei 
	ori pentru fiecare lista de magazine, prima data pentru minimarket, apoi 
	pentru mediummarket si la final pentru hypermarket. De asemenea, si 
	facturile sunt ordonate crescator dupa totalul cu taxe pe magazin.

Task 2:
	Clase:
	* StartPage: pagina de start este pentru logare. 
		- username: alice.suiu
		- password: florenta
		- in cazul in care user-ul sau parola sunt incorecte va aparea un 
		mesaj de informare in acest scop. Daca au fost introduse credentialele 
		corect se poate intra in meniu fie apasand butonul enter din 
		fereastra, fie butonul enter de la tastatura.

	* LinkedPage: pagina de meniu.
		- contine trei butoane: unul pentru a intra in pagina de incarcare a 
		fisierelor, unul pentru a intra in pagina cu gestiune produse si unul 
		pentru statisticile pe magazine.
		- chiar daca se intra in una din aceste pagini, cea de meniu ramane 
		mereu pe fundal pentru a fi accesata mai usor.

	* Load_Create_Files: pagina de incarcare a fisierelor.
		- exista 4 butoane: unul prin intermediul caruia utilizatorul trebuie 
		sa aleaga fisierul "facturi.txt", unul pentru fisierul "taxe.txt", 
		unul pentru fisierul "produse.txt" si unul prin care utilizatorul 
		alege calea unde va fi stocat fisierul "out.txt".
		- daca utilizatorul nu alege corect fisierele va aparea un mesaj 
		corespunzator.
		- dupa ce utilizatorul a selectat fisierele se creeaza obiectul 
		gestiune si fisierul "out.txt".

	* ProductsFrame: pagina de afisare si administrare a produselor din 
	fisierul "produse.txt".
		- exista un tabel cu produse in care se poate adauga un produs, doar 
		daca acesta nu exista deja.
		- se poate adauga un produs cu o tara inexistenta pana atunci in 
		tabel, insa categoria produsului adaugat trebuie sa fie una din cele 
		existente deja, altfel se va afisa mesajul "Categoria este invalida!"
		- produsele din tabel pot fi sortate dupa orice caracteristica 
		(denumire, categorie, tara, pret).
		- se poate sterge orice produs din tabel
		- se poate edita un produs, insa doar denumirea / pretul pot fi 
		modificate.
		- se poate cauta un produs dupa denumire/categorie/tara/pret ori 
		separat ori se pot da toate cele 4 caracteristici deodata. Daca 
		produsul nu se afla in tabel se va afisa un mesaj corespunzator. 
		- dupa orice operatie de adaugare/editare/stergere fisierul 
		"produse.txt" este actualizat.

	* StatisticFrame: pagina de afisare a statisticilor.
		- pentru afisarea atat a magazinului cu cele mai mari vanzari pentru 
		fiecare tara in parte si a datelor acestuia, cat si pentru magazinul 
		cu cele mai mari vanzari pentru fiecare categorie in parte si a 
		datelor acestuia am folosit un JComboBox, iar daca cumva a fost 
		adaugata o noua tara in pagina pentru gestiunea produselor, tara 
		neinfluentand acel magazin, respectiv nu se va afisa nimic pentru 
		aceea tara.
