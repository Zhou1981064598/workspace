set MATLAB=E:\software\MATLAB_2018b

cd .

if "%1"=="" ("E:\software\MATLAB_2018b\bin\win64\gmake"  -f demo.mk all) else ("E:\software\MATLAB_2018b\bin\win64\gmake"  -f demo.mk %1)
@if errorlevel 1 goto error_exit

exit /B 0

:error_exit
echo The make command returned an error of %errorlevel%
An_error_occurred_during_the_call_to_make
