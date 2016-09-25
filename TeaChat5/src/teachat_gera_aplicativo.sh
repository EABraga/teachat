#!/bin/bash
clear
echo 'Script de geração de aplicações E.A.Braga (Aluno Especial UNB - 2016, Eng. de Software)'
echo '---------------------------------------------------------------------------------------'
echo ''


echo '01. Limpa .class antigas da pasta final;'
rm -f /home/garu/workspace2/TeaChat5/src/final/*.*
echo ''
echo '***************************************************************************************************************'
echo ''

echo '02. Limpa .class antigas da pasta server;'
rm -f /home/garu/workspace2/TeaChat5/src/server/*.class
echo ''

# SERVER
# ------
if [ "X$1" != "X" ]; then

	if [ "$1" == "ALL" ] || [ "$1" == "server" ]; then
		echo '	02.01. Gera server.jar;'
		javac -nowarn server/ListaNegra.java server/Server.java server/ServerGUI.java server/ServerPlugin.java server/ServerPluginStarter.java
		jar cvmf server/MANIFEST.MF final/server.jar geral/* server/*.class
		chmod +x final/server.jar
		echo ''
		echo '***************************************************************************************************************'
		echo 'merda' > final/listanegra.txt
	fi
else
	echo ""	
	echo "ERRO!"
	echo "-----"	
	echo "Digite $0 ALL (para compilar e gerar todos os aplicativos);"
	echo "Digite $0 server (para compilar e gerar o aplicativo server);"
	echo "Digite $0 client (para compilar e gerar o aplicativo client);"
	echo "Digite $0 cripto (para compilar e gerar o aplicativo cripto);"
	echo ""
	echo ""
	echo "Desenvolvido por E.A.Braga 09/2016."
	exit 1
fi

# CLIENT
# ------
if [ "$1" == "ALL" ] || [ "$1" == "client" ]; then
	echo ''
	echo '03. Limpa .class antigas da pasta client;'
	rm -f /home/garu/workspace2/TeaChat5/src/client/*.class
	echo ''
	echo '	03.01. Gera client.jar;'
	javac -nowarn -Xlint client/login.java client/Client.java client/LeLog.java client/ClientGui.java
	jar cvmf client/MANIFEST.MF final/client.jar geral/* client/*.class
	chmod +x final/client.jar
	echo ''
	echo '***************************************************************************************************************'
fi

if [ "$1" == "ALL" ] || [ "$1" == "cripto" ]; then
	echo ''
	echo '04. Limpa .class antigas da pasta cripto;'
	rm -f /home/garu/workspace2/TeaChat5/src/cripto/*.class
	echo ''
	echo '	04.01. Gera cripto.jar;'
	javac -nowarn -Xlint cripto/EncriptaDecriptaDES.java cripto/Teste.java
	jar cvmf cripto/MANIFEST.MF final/cripto.jar cripto/*.class
	chmod +x final/cripto.jar
	echo ''
fi

