# UppaMaps
Projet M2 TI Android Studio application 

Projet PSMSF (Android) 2021/2022 – Master 2 Technologies de l'Internet – O. Le Goaer
UPPA Maps

La direction de l'UPPA souhaite que son personnel et les étudiants puisse
facilement se repérer entre les différents batiments, sur les différents
campus.

GUI
L'application est constituée de 3 écrans :
1. Liste des batiments : l'utilisateurs choisi un batiment des campus
Pau/Anglet/Mont de Marsan dans lequel il souhaite se rendre (idéalement suggéré
en fonction de sa position actuelle)
2. Navigation : bascule vers Google navigation pour guider l'utilisateur pas à pas,
dans le mode "à pied" ou "vélo" (voir code en annexe)
3. Panneau de préférence : permet à l'utilisateur de configurer son mode de
déplacement favori ("à pied" par défaut, ou "à velo")
Cette application étant au départ destinée au téléchargement sur Google Play Store au
nom de l'UPPA, l'app ne doit souffrir d'aucun défaut et respecter au mieux la charte
graphique de l'UPPA. Tous les cas particulier doivent être gérés : perte de connectivité,
Gmaps absent, etc. Aucun crash ou installation ratée ne sera toléré pour ce projet
particulier.

Database

Un simple fichier .csv positionné dans /assets/ fera ici office de base de données. Ce
fichier pourra être amené à évoluer, mais il suffira de le remplacer et de republier l'app sur
le Play Store.
