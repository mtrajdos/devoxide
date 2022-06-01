first_observer <- read.csv(".", skip=1) #Add the .csv for the first observer's scoring here, within quotation marks (for example "SubjectC_scores.csv")
second_observer <- read.csv("./UvA/Wonambi/Data/zMax/Sebastiaan/09-03-2022/09-03-2022_SebastiaanScores-SebastiaanScorer.csv", skip=1) #Add the .csv for the second observer's scoring here
stages <- c("NREM1", "NREM2", "NREM3", "REM", "Wake", "Movement", "Undefined", "Unknown", "Artefact") #Keep like this, unless you want to include certain annotations that are not in here (not sure if you even can)

first_observer$stage = as.character(first_observer$stage)
levels(first_observer$stage)

second_observer$stage = as.character(second_observer$stage)
levels(second_observer$stage)

expected_agreement <- function(first_observer, second_observer, stage){
  (sum(first_observer$stage == stage)/length(first_observer$stage)) * (sum(second_observer$stage == stage)/length(second_observer$stage))
}

p_expected <- function(first_observer, second_observer, stages){
  x <- numeric(6)
  for (i in 1:(length(stages))){
    x[i] <- expected_agreement(first_observer, second_observer, stages[i])
  }
  p_expected <- sum(x)
  return(p_expected)
}

score <- first_observer$stage == second_observer$stage

agreement <- first_observer$stage == second_observer$stage
p_o <- sum(score)/length(first_observer$stage)
p_e <- p_expected(first_observer, second_observer, stages)
kappa <- (p_o - p_e)/(1-p_e)

agreement_stage <- numeric(length(first_observer$stage))
for (i in 1:length(first_observer$stage)){
  if (first_observer$stage[i] == second_observer$stage[i]){
    agreement_stage[i] <- paste0("Correct ", first_observer$stage[i])
  }
  else {
    agreement_stage[i] <- "Incorrect"
  }
}

Agreement_N1 <- sum(agreement_stage == "Correct NREM1")/max(c(sum(first_observer$stage == "NREM1"), sum(second_observer$stage == "NREM1")))
Agreement_N2 <- sum(agreement_stage == "Correct NREM2")/max(c(sum(first_observer$stage == "NREM2"), sum(second_observer$stage == "NREM2")))
Agreement_N3 <- sum(agreement_stage == "Correct NREM3")/max(c(sum(first_observer$stage == "NREM3"), sum(second_observer$stage == "NREM3")))
Agreement_REM <- sum(agreement_stage == "Correct REM")/max(c(sum(first_observer$stage == "REM"), sum(second_observer$stage == "REM")))
Agreement_Wake <- sum(agreement_stage == "Correct Wake")/max(c(sum(first_observer$stage == "Wake"), sum(second_observer$stage == "Wake")))
if (sum(first_observer$stage == "Movement") == 0 | sum(second_observer$stage == "Movement") == 0){
  Agreement_Movement <- "Not identified by both raters"
} else {
  Agreement_Movement <- sum(agreement_stage == "Correct Movement")/max(c(sum(first_observer$stage == "Movement"), sum(second_observer$stage == "Movement")))
}
if(sum(first_observer$stage == "Undefined") == 0 | sum(second_observer$stage == "Undefined") == 0) {
  Agreement_Undefined <- "Not identified by both raters"
} else {
  Agreement_Undefined <- sum(agreement_stage == "Correct Undefined")/max(c(sum(first_observer$stage == "Undefined"), sum(second_observer$stage == "Undefined")))
}
if (sum(first_observer$stage == "Unknown") == 0 | sum(second_observer$stage == "Unknown") == 0){
  Agreement_Unknown <- "Not identified by both raters"
} else{
  Agreement_Unknown <- sum(agreement_stage == "Correct Unknown")/max(c(sum(first_observer$stage == "Unknown"), sum(second_observer$stage == "Unknown")))
}
if (sum(first_observer$stage == "Artefact") == 0 | sum(second_observer$stage == "Artefact") == 0) {
  Agreement_Artefact <- "Not identified by both raters"
} else {
  Agreement_Artefact <- sum(agreement_stage == "Correct Artefact")/max(c(sum(first_observer$stage == "Artefact"), sum(second_observer$stage == "Artefact")))
}

Agreement_scores <- data.frame(Agreement_N1, Agreement_N2, Agreement_N3, Agreement_REM, Agreement_Wake, Agreement_Movement, Agreement_Undefined, Agreement_Unknown, Agreement_Artefact)

print(Agreement_scores)

print(paste0("The agreement score between these two observers is ", p_o))
print(paste0("The Kappa score between these two observers is ", kappa))

#To add:
#Exclude Undefined for Cohen's Kappa
#Include disagreed epochs with the stages that both raters gave to the epoch
#Make it so that if two epochs are disagreed upon are next to each other, the start and end time are given together, instead of for every epoch separately
#Include confusion matrices

#My analysis: Time frequency analysis
