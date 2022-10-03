# SQL 
SELECT "NAME", (goals_for_host.sum1+goals_for_guest.sum2), 
		(goals_against_host.sum4+goals_against_guest.sum3),
		points.sss 
FROM "TEAMS",
	(SELECT "HOST_TEAM" as HT1, SUM ("HOST_GOALS") as sum1
	FROM  "MATCHES"
	GROUP BY HT1) as goals_for_host, 

	(SELECT "GUEST_TEAM" as GT1, SUM ("GUEST_GOALS") as sum2
	FROM "MATCHES"
	GROUP BY GT1) as goals_for_guest,
	
	(SELECT "HOST_TEAM" as HT2 , SUM ("GUEST_GOALS") as sum3
	FROM "MATCHES"
	GROUP BY HT2) as goals_against_guest,
	
	(SELECT "GUEST_TEAM" as GT2, SUM ("HOST_GOALS") as sum4
	FROM "MATCHES"
	GROUP BY GT2) as goals_against_host,
	
	
	(SELECT "NAME" n, (coalesce(count1, 0) + coalesce(count2, 0) + coalesce(count3, 0) + coalesce(count4, 0)) sss 
	FROM "TEAMS"
	LEFT JOIN (SELECT "HOST_TEAM" as htp1, (COUNT("MATCH_ID")*3) as count1
		FROM "MATCHES"
		WHERE ("HOST_GOALS">"GUEST_GOALS")
		GROUP BY htp1) as points_win_host
	ON "ID"=htp1
	LEFT JOIN (SELECT "GUEST_TEAM" as gtp1, (COUNT("MATCH_ID")*3) as count2
		FROM "MATCHES"
		WHERE ("HOST_GOALS"<"GUEST_GOALS")
		GROUP BY gtp1) as points_win_guest
	ON "ID"=gtp1
	LEFT JOIN (SELECT "HOST_TEAM" as htp2, COUNT("MATCH_ID") as count3
		FROM "MATCHES"
		WHERE ("HOST_GOALS"="GUEST_GOALS")
		GROUP BY htp2) as points_draw_host
	ON "ID"=htp2
	LEFT JOIN (SELECT "GUEST_TEAM" as gtp2, COUNT("MATCH_ID") as count4
		FROM "MATCHES"
		WHERE ("HOST_GOALS"="GUEST_GOALS")
		GROUP BY gtp2) as points_draw_guest
	ON "ID"=gtp2) points
WHERE (HT1=HT2) AND (GT1=GT2) AND (HT1=GT1) AND("ID"=HT1) AND (points.n="NAME")
ORDER BY sss DESC
