Add-Type -AssemblyName System.Windows.Forms
$fileBrowser = New-Object System.Windows.Forms.OpenFileDialog -Property @{ 
    Multiselect = $True
    Filter = "Zip Files|*.zip;*.rar"
}

if ($fileBrowser.ShowDialog() -eq "OK") {
    $folderOutput = New-Object System.Windows.Forms.FolderBrowserDialog 
    if ($folderOutput.ShowDialog() -eq "OK") {
        $WinRar = "C:\Program Files\WinRAR\winrar.exe"        
        $selectedFiles = $FileBrowser.FileNames
        foreach ($file in $selectedFiles) {
            $directoryContents = $file.Split(".")[0] 
            &$Winrar x $file $directoryContents"\"
            Get-Process winrar | Wait-Process
            Move-Item -Path $directoryContents"\*" -Destination $folderOutput.SelectedPath
            Remove-Item $directoryContents -Recurse
        }
    }
}