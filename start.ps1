param (
    [string]$year,
    [int]$day_short
)
$day = $day_short.ToString("D2")

if (-Not (Test-Path -Path "src\input\Day$day.txt"))
{
    Write-Output "getting input..."
    $session = [Microsoft.PowerShell.Commands.WebRequestSession]::new()
    $cookie = [System.Net.Cookie]::new('session', $Env:AOC_COOKIE)
    $session.Cookies.Add('https://adventofcode.com', $cookie)
    Invoke-RestMethod -Uri "https://adventofcode.com/$year/day/$day_short/input" -WebSession $session  -OutFile "src\input\Day$day.txt"
}

if (-Not (Test-Path -Path "src\Day$day.kt"))
{
    Write-Output "creating stub..."
    $content = Get-Content -Path "src\Template.kt" | ForEach-Object { $_ -replace "00", $day }
    $content | Set-Content -Path "src\Day$day.kt"
}

if (-Not (Test-Path -Path "src\input\Day$day`_test.txt"))
{
    Write-Output "creating test input..."
    New-Item -ItemType File -Path "src\input\Day$day`_test.txt" -Force | Out-Null
}

git add "src\Day$day.kt"

idea64.exe src\Day$day.kt
idea64.exe src\input\Day${day}_test.txt
