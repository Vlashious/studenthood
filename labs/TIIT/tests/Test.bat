@echo off
@echo TEST %2
@echo TEST %2 >> result.txt
copy Input%2.txt input.txt > nul
Timer %1 1000 65536 >> result.txt 
Checker Input%2.txt output.txt Answer%2.txt >> result.txt
if exist input.txt del input.txt
if exist output.txt del output.txt
