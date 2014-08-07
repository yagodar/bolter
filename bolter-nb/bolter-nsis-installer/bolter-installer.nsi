# This installs two files, bolter.jar and logo-bolter.ico, creates a start menu shortcut, builds an uninstaller, and
# adds uninstall information to the registry for Add/Remove Programs

# To get started, put this script into a folder with the two files (bolter.jar, logo-bolter.ico, and license.rtf -
# You'll have to create these yourself) and run makensis on it

# If you change the names "bolter.jar", "logo-bolter.ico", or "license.rtf" you should do a search and replace - they
# show up in a few places.
# All the other settings can be tweaked by editing the !defines at the top of this script
!define APPNAME "Bolter"
!define COMPANYNAME "YagodarCo"
!define DESCRIPTION "Программа для организации интернет поиска по определенным сайтам и по определенному промежутку времени."
# These three must be integers
!define VERSIONMAJOR 1
!define VERSIONMINOR 0
!define VERSIONBUILD 0
# These will be displayed by the "Click here for support information" link in "Add/Remove Programs"
# It is possible to use "mailto:" links in here to open the email client
!define HELPURL "http://www.vk.com/bolter.app" # "Support Information" link
!define UPDATEURL "http://www.vk.com/bolter.app" # "Product Updates" link
!define ABOUTURL "http://www.vk.com/bolter.app" # "Publisher" link
# This is the size (in kB) of all the files copied into "Program Files"
!define INSTALLSIZE 7233 # TODO

!define JRE_VER_REQ "1.4.1"

RequestExecutionLevel admin ;Require admin rights on NT6+ (When UAC is turned on)

InstallDir "$PROGRAMFILES\${COMPANYNAME}\${APPNAME}"

# rtf or txt file - remember if it is txt, it must be in the DOS text format (\r\n)
LicenseData "license-bolter.rtf"
# This will be in the installer/uninstaller's title bar
Name "${COMPANYNAME} - ${APPNAME}"
Icon "icon-installer.ico"
OutFile "${APPNAME}-${VERSIONMAJOR}.${VERSIONMINOR}.${VERSIONBUILD}-installer.exe"

!include LogicLib.nsh

# Just three pages - license agreement, install location, and installation
page custom CheckJREPage CheckJRELeave
page license
page directory
page instfiles

!macro VerifyUserIsAdmin
UserInfo::GetAccountType
pop $0
${If} $0 != "admin" ;Require admin rights on NT4+
        MessageBox mb_iconstop "Требуются права Администратора!"
        setErrorLevel 740 ;ERROR_ELEVATION_REQUIRED
        quit
${EndIf}
!macroend

function .onInit
	setShellVarContext all
	!insertmacro VerifyUserIsAdmin
functionEnd

Function CheckJREPage
        Push "${JRE_VER_REQ}"
        Call DetectJRE
        Messagebox MB_OK "Done checking JRE version"
        Exch $0	; Get return value from stack
        StrCmp $0 "0" NoFound
        StrCmp $0 "-1" FoundOld
        Goto JREAlreadyInstalled

        FoundOld:
        MessageBox MB_OK "Найдена старая версия JRE. Для работы ${AppName} требуется более новая версия JRE - ${JRE_VER_REQ}. Установка будет прекращена."
        Abort
        
        NoFound:
        MessageBox MB_OK "JRE не найдено! Установка будет прекращена."
        Abort
        
        JREAlreadyInstalled:
        Pop $0		; Restore $0
        
FunctionEnd

Function CheckJRELeave
        Abort
FunctionEnd

Function DetectJRE
          Exch $0	; Get version requested
        		; Now the previous value of $0 is on the stack, and the asked for version of JDK is in $0
          Push $1	; $1 = Java version string (ie 1.5.0)
          Push $2	; $2 = Javahome
          Push $3	; $3 and $4 are used for checking the major/minor version of java
          Push $4
          MessageBox MB_OK "Detecting JRE"
          ReadRegStr $1 HKLM "SOFTWARE\JavaSoft\Java Runtime Environment" "CurrentVersion"
          MessageBox MB_OK "Read : $1"
          StrCmp $1 "" DetectTry2
          ReadRegStr $2 HKLM "SOFTWARE\JavaSoft\Java Runtime Environment\$1" "JavaHome"
          MessageBox MB_OK "Read 3: $2"
          StrCmp $2 "" DetectTry2
          Goto GetJRE

        DetectTry2:
          ReadRegStr $1 HKLM "SOFTWARE\JavaSoft\Java Development Kit" "CurrentVersion"
          MessageBox MB_OK "Detect Read : $1"
          StrCmp $1 "" NoFound
          ReadRegStr $2 HKLM "SOFTWARE\JavaSoft\Java Development Kit\$1" "JavaHome"
          MessageBox MB_OK "Detect Read 3: $2"
          StrCmp $2 "" NoFound

        GetJRE:
        ; $0 = version requested. $1 = version found. $2 = javaHome
          MessageBox MB_OK "Getting JRE"
          IfFileExists "$2\bin\java.exe" 0 NoFound
          StrCpy $3 $0 1			; Get major version. Example: $1 = 1.5.0, now $3 = 1
          StrCpy $4 $1 1			; $3 = major version requested, $4 = major version found
          MessageBox MB_OK "Want $3 , found $4"
          IntCmp $4 $3 0 FoundOld FoundNew
          StrCpy $3 $0 1 2
          StrCpy $4 $1 1 2			; Same as above. $3 is minor version requested, $4 is minor version installed
          MessageBox MB_OK "Want $3 , found $4"
          IntCmp $4 $3 FoundNew FoundOld FoundNew

        NoFound:
          MessageBox MB_OK "JRE not found"
          Push "0"
          Goto DetectJREEnd

        FoundOld:
          MessageBox MB_OK "JRE too old: $3 is older than $4"
        ;  Push ${TEMP2}
          Push "-1"
          Goto DetectJREEnd
        FoundNew:
          MessageBox MB_OK "JRE is new: $3 is newer than $4"

          Push "$2\bin\java.exe"
        ;  Push "OK"
        ;  Return
           Goto DetectJREEnd
        DetectJREEnd:
        	; Top of stack is return value, then r4,r3,r2,r1
        	Exch	; => r4,rv,r3,r2,r1,r0
        	Pop $4	; => rv,r3,r2,r1r,r0
        	Exch	; => r3,rv,r2,r1,r0
        	Pop $3	; => rv,r2,r1,r0
        	Exch 	; => r2,rv,r1,r0
        	Pop $2	; => rv,r1,r0
        	Exch	; => r1,rv,r0
        	Pop $1	; => rv,r0
        	Exch	; => r0,rv
        	Pop $0	; => rv
FunctionEnd

section "install"
	# Files for the install directory - to build the installer, these should be in the same directory as the install script (this file)
	setOutPath $INSTDIR
	# Files added here should be removed by the uninstaller (see section "uninstall")
	file "bolter.jar"
	file "logo-bolter.ico"
	# Add any other files for the install directory (license files, app data, etc) here

	# Uninstaller - See function un.onInit and section "uninstall" for configuration
	writeUninstaller "$INSTDIR\uninstall.exe"

	# Start Menu
	createDirectory "$SMPROGRAMS\${COMPANYNAME}"
	createShortCut "$SMPROGRAMS\${COMPANYNAME}\${APPNAME}.lnk" "$INSTDIR\bolter.jar" "" "$INSTDIR\logo-bolter.ico"

	# Registry information for add/remove programs
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "DisplayName" "${COMPANYNAME} - ${APPNAME} - ${DESCRIPTION}"
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "UninstallString" "$\"$INSTDIR\uninstall.exe$\""
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "QuietUninstallString" "$\"$INSTDIR\uninstall.exe$\" /S"
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "InstallLocation" "$\"$INSTDIR$\""
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "DisplayIcon" "$\"$INSTDIR\logo-bolter.ico$\""
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "Publisher" "$\"${COMPANYNAME}$\""
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "HelpLink" "$\"${HELPURL}$\""
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "URLUpdateInfo" "$\"${UPDATEURL}$\""
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "URLInfoAbout" "$\"${ABOUTURL}$\""
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "DisplayVersion" "$\"${VERSIONMAJOR}.${VERSIONMINOR}.${VERSIONBUILD}$\""
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "VersionMajor" ${VERSIONMAJOR}
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "VersionMinor" ${VERSIONMINOR}
	# There is no option for modifying or repairing the install
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "NoModify" 1
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "NoRepair" 1
	# Set the INSTALLSIZE constant (!defined at the top of this script) so Add/Remove Programs can accurately report the size
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "EstimatedSize" ${INSTALLSIZE}
sectionEnd

# Uninstaller

function un.onInit
	SetShellVarContext all

	#Verify the uninstaller - last chance to back out
	MessageBox MB_OKCANCEL "Вы уверены удалить ${APPNAME}?" IDOK next
		Abort
	next:
	!insertmacro VerifyUserIsAdmin
functionEnd

section "uninstall"

	# Remove Start Menu launcher
	delete "$SMPROGRAMS\${COMPANYNAME}\${APPNAME}.lnk"
	# Try to remove the Start Menu folder - this will only happen if it is empty
	rmDir "$SMPROGRAMS\${COMPANYNAME}"

	# Remove files
	delete $INSTDIR\bolter.jar
	delete $INSTDIR\logo-bolter.ico

	# Always delete uninstaller as the last action
	delete $INSTDIR\uninstall.exe

	# Try to remove the install directory - this will only happen if it is empty
	rmDir $INSTDIR

	# Remove uninstaller information from the registry
	DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}"
sectionEnd