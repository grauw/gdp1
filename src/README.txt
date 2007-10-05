Laurens Holst - 0122173
Milan de Graaf - 3117308
De globale opbouw van ons programma.

Het programma bevat vijf klassen:
1. MainWindow
2. Animatie
3. ThreadCounter
4. Quicksort
5. tekenCanvas


Als start klasse hebben wij de klasse MainWindow. 
De methode main roept hierin het aanmaken van een nieuwe MainWindow.
De constructor zet het frame (extended klasse) op de juiste grootte en maakt vervolgens een Quicksort klasse aan
en maakt daarna een nieuwe animatie klasse aan.
Deze animatie klasse creert een tekenCanvas die wordt toegevoegd aan het frame van MainWindow.
De MainWindow constructor creeërt vervolgens een container met alle TextFields daarin en voegt deze
container toe aan het frame.
Dan wordt een Thread voor de animatie gestart en wordt de Quicksort die eerder al was aangemaakt gestart.

Aan de frame wordt een WindowListener toegevoegd, zodat als het programma wordt afgesloten
de threads gestopt worden en alle resources worden weggegooid.

Aan de TextFields worden een ActionListener en een FocusListener toegevoegd, zodat de waardes veranderdt kunnen worden.
De FocusListener hebben we toegevoegd als alternatief, zodat er niet steeds na een invoer op enter gedrukt hoeft te worden.
