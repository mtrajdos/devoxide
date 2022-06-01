$parameters = @{
    Name = "Y"
    PSProvider = "FileSystem"
    Root = "\\fmgstorage.fmg.uva.nl\psychology$\Projects\Project Sleep and cognition"
    Description = "Maps to FMG Project Sleep and Cognition storage drive."
    Scope = "Global"
    Credential = "uva\11079355"
}
New-PSDrive @parameters -Persist