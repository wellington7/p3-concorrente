#CRIA UMA ÁRVORE DE DIRETÓRIOS COM 3 NIVEIS
cria_diretorios_goFsckYourself()
{
	echo ""
	echo "************************************************"
	echo "***** PROGRAMAÇÃO CONCORRENTE - PROVA 3 ********"
	echo "***** ALUNOS: WELLINGTON ARAUJO SILVA **********"
	echo "************* GILMAR GONZAGA DA SILVA **********"
	echo "************* GUSTAVO LUIZ BISPO DOS SANTOS*****"
	echo "************************************************"
	echo ""
	echo "* Criando árvore de diretórios para execução do FSCK"
	echo  "Prova3/treeRoot"
	mkdir Prova3/treeRoot
	echo  "Prova3/treeRoot/lvl1-A/"
	mkdir Prova3/treeRoot/lvl1-A/
	echo  "Prova3/treeRoot/lvl1-B"
	mkdir Prova3/treeRoot/lvl1-B/
	echo  "Prova3/treeRoot/lvl1-C_dmg/"
	mkdir Prova3/treeRoot/lvl1-C_dmg/
	echo  "Prova3/treeRoot/lvl1-A/lvl2-A/"
	mkdir Prova3/treeRoot/lvl1-A/lvl2-A/
	echo  "Prova3/treeRoot/lvl1-A/lvl2-B_dmg/"
	mkdir Prova3/treeRoot/lvl1-A/lvl2-B_dmg/
	echo  "Prova3/treeRoot/lvl1-A/lvl2-C/"
	mkdir Prova3/treeRoot/lvl1-A/lvl2-C/
	echo  "Prova3/treeRoot/lvl1-B/lvl2-A/"
	mkdir Prova3/treeRoot/lvl1-B/lvl2-A/
	echo  "Prova3/treeRoot/lvl1-B/lvl2-B/"
	mkdir Prova3/treeRoot/lvl1-B/lvl2-B/
	echo  "Prova3/treeRoot/lvl1-C_dmg/lvl2-A/"
	mkdir Prova3/treeRoot/lvl1-C_dmg/lvl2-A/
	echo  "Prova3/treeRoot/lvl1-A/lvl2-A/lvl3-A_dmg/"
	mkdir Prova3/treeRoot/lvl1-A/lvl2-A/lvl3-A_dmg/
	echo  "Prova3/treeRoot/lvl1-A/lvl2-A/lvl3-B/"
	mkdir Prova3/treeRoot/lvl1-A/lvl2-A/lvl3-B/
	echo  "Prova3/treeRoot/lvl1-C_dmg/lvl2-A/lvl3-A/"
	mkdir Prova3/treeRoot/lvl1-C_dmg/lvl2-A/lvl3-A/
	echo "* Árvore de diretórios criada para execução do FSCK"
}

#POVOA COM FILES PARA EXECUÇÃO DO FSCK
povoa_arquivos()
{
	cd Prova3/treeRoot
	echo > fileRoot_dmg.txt
	cd lvl1-C_dmg
	echo > file1.txt
	cd lvl2-A
	echo > file2_dmg.txt
	cd ../../../..
}

#EXECUTA O PROGRAMA JAVA
executa_programa_java() 
{
	echo ""
	echo "* Iniciando a execução do .jar"
	java -jar Prova3JAR.jar
	echo "* Execução do .jar finalizada"
}

#PROGRAMA PRINCIPAL
cria_diretorios_goFsckYourself
povoa_arquivos
executa_programa_java