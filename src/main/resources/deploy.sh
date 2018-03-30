#!/bin/bash
rsync -auvz -e "ssh -i ~/.ssh/websiteKey" /home/raphael/cs350/Blue2/build/docs/ rsandor@atria.cs.odu.edu:./
rsync -auvz -e "ssh -i ~/.ssh/websiteKey" /home/raphael/cs350/Blue2/build/reports/ rsandor@atria.cs.odu.edu:./
