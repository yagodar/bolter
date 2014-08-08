!include "MUI.nsh"
!include "x64.nsh"

!define COMPANYNAME "Yagodar"
!define APPNAME "Bolter"

!define VERSIONMAJOR 1
!define VERSIONMINOR 0
!define VERSIONBUILD 0

!define HELPURL "http://www.vk.com/bolter.app"
!define UPDATEURL "http://www.vk.com/bolter.app"
!define ABOUTURL "http://www.vk.com/bolter.app"

!define JRE_VER_REQ "1.7u67"
!define JRE_X86_URL "http://javadl.sun.com/webapps/download/AutoDL?BundleId=95141" #7u67
!define JRE_X64_URL "http://javadl.sun.com/webapps/download/AutoDL?BundleId=95141" #7u67

!define JRE_NO_FOUND_MARK "JRE_NO"
!define JRE_OLD_FOUND_MARK "JRE_OLD"
!define JRE_OK_FOUND_MARK "JRE_OK"

!define INSTALLSIZE 7233 # TODO

!define APP_FILES_DIR "app-files"
!define APP_FILE_NAME "bolter.jar"
!define APP_FILE_ICO "logo-bolter.ico"

!define INSTALLER_FILES_DIR "installer-files"
!define INSTALLER_FILE_LICENSE "license-bolter.rtf"
!define INSTALLER_FILE_ICO "icon-installer.ico"

!define DIST_FILES_DIR "dist"

!define MUI_ICON "${INSTALLER_FILES_DIR}\${INSTALLER_FILE_ICO}"
!define MUI_PAGE_CUSTOMFUNCTION_PRE CheckJRE
!define MUI_PAGE_CUSTOMFUNCTION_LEAVE CheckJRELeave
  
RequestExecutionLevel admin

Name "${APPNAME} ${VERSIONMAJOR}.${VERSIONMINOR}.${VERSIONBUILD}"
OutFile "${DIST_FILES_DIR}\${APPNAME}-${VERSIONMAJOR}.${VERSIONMINOR}.${VERSIONBUILD}-installer.exe"
InstallDir "$PROGRAMFILES\${COMPANYNAME}\${APPNAME}"

!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_LICENSE "${INSTALLER_FILES_DIR}\${INSTALLER_FILE_LICENSE}"
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES

!insertmacro MUI_UNPAGE_WELCOME
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

!insertmacro MUI_LANGUAGE "Russian"

!macro VerifyUserIsAdmin
UserInfo::GetAccountType
pop $0
${If} $0 != "admin" ;Require admin rights on NT4+
        MessageBox MB_OK|MB_ICONSTOP "Требуются права Администратора!"
        setErrorLevel 740 ;ERROR_ELEVATION_REQUIRED
        quit
${EndIf}
!macroend

function .onInit
	setShellVarContext all
	!insertmacro VerifyUserIsAdmin
functionEnd

Function CheckJRE
        Call DetectJRE
        Pop $R0
        StrCmp $R0 ${JRE_OK_FOUND_MARK} Done InstallJRE

        InstallJRE:
            Call DownloadAndInstallJRE
            Call DetectJRE
            Pop $R0
            StrCmp $R0 ${JRE_OK_FOUND_MARK} Done Quit

        Quit:
             MessageBox MB_OK|MB_ICONSTOP "Загрузка и установка Java не удалась!"
             Call Quit

        Done:
            Return

FunctionEnd

Function CheckJRELeave
        Return
FunctionEnd

Function DetectJRE
         Push $R0 # result
         Push $1 # JRE_VER_CUR
         Push $2 # JRE_HOME
         Push $3 # JRE_VER_CUR_MAJ
         Push $4 # JRE_VER_REQ_MAJ
         Push $5 # JRE_VER_CUR_MINOR
         Push $6 # JRE_VER_REQ_MINOR

         ReadRegStr $1 HKLM "SOFTWARE\JavaSoft\Java Runtime Environment" "CurrentVersion"
         StrCmp $1 "" NoFound DetectJREHome

        DetectJREHome:
         ReadRegStr $2 HKLM "SOFTWARE\JavaSoft\Java Runtime Environment\$1" "JavaHome"
         StrCmp $2 "" NoFound GetJRE

        GetJRE:
          IfFileExists "$2\bin\java.exe" 0 NoFound

          StrCpy $3 $1 1
          StrCpy $4 ${JRE_VER_REQ} 1
          IntCmp $3 $4 0 FoundOld

          StrCpy $5 $1 1 2
          StrCpy $6 ${JRE_VER_REQ} 1 2
          IntCmp $5 $6 0 FoundOld

          StrCpy $R0 ${JRE_OK_FOUND_MARK}
          Goto Done

        NoFound:
          StrCpy $R0 ${JRE_NO_FOUND_MARK}
          Goto Done

        FoundOld:
          StrCpy $R0 ${JRE_OLD_FOUND_MARK}
          Goto Done

        Done:
             Pop $6
             Pop $5
             Pop $4
             Pop $3
             Pop $2
             Pop $1
             Exch $R0
             Return
FunctionEnd

Function DownloadAndInstallJRE
        Push $1 # PATH_TEMP_EXE

        ${If} ${RunningX64}
              StrCpy $R1 ${JRE_X64_URL}
        ${Else}
              StrCpy $R1 ${JRE_X86_URL}
        ${EndIf}

        MessageBox MB_OKCANCEL|MB_ICONQUESTION "Программа ${APPNAME} использует Java Runtime Environment ${JRE_VER_REQ}. \
                                                В системе не найдено подходящей версии. Загрузить и установить? \
                                                ($R1) \
                                                (При отрицательном ответе установка ${APPNAME} будет отменена)" IDOK DownloadAndInstall IDCANCEL Done

        DownloadAndInstall:
                StrCpy $1 "$TEMP\Java Runtime Environment.exe"
                nsisdl::download /TIMEOUT=30000 $R1 $1
                Pop $R0 ;Get the return value
                StrCmp $R0 "success" DoneDownload FailedDownload


        DoneDownload:
                ExecWait $1
                Delete $1
                GoTo Done

        FailedDownload:
                MessageBox MB_OK|MB_ICONSTOP "Загрузка не удалась: $R0"
                Call Quit

        Done:
            Pop $1
FunctionEnd

Function Quit
        MessageBox MB_OK|MB_ICONSTOP "Установка ${APPNAME} отменена!"
        Quit
FunctionEnd

Section "Install"
	# Files for the install directory - to build the installer, these should be in the same directory as the install script (this file)
	SetOutPath $INSTDIR
	# Files added here should be removed by the uninstaller (see section "uninstall")
	file "${APP_FILES_DIR}\*.*"
	# Add any other files for the install directory (license files, app data, etc) here

	# Uninstaller - See function un.onInit and section "uninstall" for configuration
	writeUninstaller "$INSTDIR\uninstall.exe"

	# Start Menu
	createDirectory "$SMPROGRAMS\${COMPANYNAME}"
	createShortCut "$SMPROGRAMS\${COMPANYNAME}\${APPNAME}.lnk" "$INSTDIR\${APP_FILE_NAME}" "" "$INSTDIR\${APP_FILE_ICO}"

        # Desktop
	createShortCut "$DESKTOP\${APPNAME}.lnk" "$INSTDIR\${APP_FILE_NAME}" "" "$INSTDIR\${APP_FILE_ICO}"

	# Registry information for add/remove programs
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "DisplayName" "${APPNAME} ${VERSIONMAJOR}.${VERSIONMINOR}.${VERSIONBUILD}"
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "UninstallString" "$\"$INSTDIR\uninstall.exe$\""
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "QuietUninstallString" "$\"$INSTDIR\uninstall.exe$\" /S"
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "InstallLocation" "$\"$INSTDIR$\""
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "DisplayIcon" "$\"$INSTDIR\${APP_FILE_ICO}$\""
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "Publisher" "${COMPANYNAME}"
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "HelpLink" "${HELPURL}"
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "URLUpdateInfo" "${UPDATEURL}"
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "URLInfoAbout" "${ABOUTURL}"
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "DisplayVersion" "${VERSIONMAJOR}.${VERSIONMINOR}.${VERSIONBUILD}"
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "VersionMajor" ${VERSIONMAJOR}
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "VersionMinor" ${VERSIONMINOR}
	# There is no option for modifying or repairing the install
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "NoModify" 1
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "NoRepair" 1
	# Set the INSTALLSIZE constant (!defined at the top of this script) so Add/Remove Programs can accurately report the size
	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}" "EstimatedSize" ${INSTALLSIZE}
sectionEnd

function un.onInit
	SetShellVarContext all

	#Verify the uninstaller - last chance to back out
	MessageBox MB_OKCANCEL "Вы уверены удалить ${APPNAME}?" IDOK next
		Abort
	next:
	!insertmacro VerifyUserIsAdmin
functionEnd

section "Uninstall"

	# Remove Start Menu launcher
	delete "$SMPROGRAMS\${COMPANYNAME}\${APPNAME}.lnk"

	# Remove Desktop launcher
	delete "$DESKTOP\${APPNAME}.lnk"

	# Try to remove the Start Menu folder - this will only happen if it is empty
	rmDir "$SMPROGRAMS\${COMPANYNAME}"

	# Remove files
	delete "$INSTDIR\*.*"

	# Always delete uninstaller as the last action
	delete "$INSTDIR\uninstall.exe"

	# Try to remove the install directory - this will only happen if it is empty
	rmDir $INSTDIR

	# Remove uninstaller information from the registry
	DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${COMPANYNAME} ${APPNAME}"
sectionEnd