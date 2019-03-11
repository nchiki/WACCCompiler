module.exports = ({
	name: 'wacc',
	rules: {
		source_file: $ => seq(
			'begin',
			repeat($.function_definition),
			repeat($.statement)
			'end'
		),

		function_definition: $ => seq(
			$.type,
			$.identifier,
			'(',
			$.paramList,
			')',
			'IS',
			repeat($.statement),
			'END'
		),

		statement: $ => choice(
			'skip',
			$.decleration,
			$.assignment,
			$.read,
			$.free,
			$.return,
			$.exit,
			$.print,
			$.println,
			$.if_statement,
			$.while_statement,
			$.stat_block,
			$.stat_list
		),

		expr: $ => choice(
			$.int_lit,
			$.bool_lit,
			$.char_lit,
			$.str_lit,
			$.pair_lit,
			$.identifier,
			$.array_elem,
			$.unary_op,
			$.binary_op,
			$.paren
		),

		decleration: $ => seq(
			$.type,
			$.identifer,
			'=',
			$.assign_rhs
		),

		assignment: $ => seq(
			$.assign_lhs,
			$.assign_rhs
		),

		read: $ => seq(
			'read',
			$.assign_lhs
		),

		free: $ => seq(
			'free',
			$.expr
		),

		return: $ => seq(
			'return',
			$.expr
		),

		exit: $ => seq(
			'exit',
			$.expr
		),

		print: $ => seq(
			'print',
			$.expr
		),

		println: $ => seq(
			'println',
			$.expr
		),

		if_statement: $ => seq(
			'if',
			$.expr,
			'then',
			$.statement,
			'else',
			$.statement,
			'fi'
		),

		assign_rhs: $ => choice(
			$.expr,
			$.array_lit,
			$.newpair,
			$.pair_elem,
			$.function_call
		),

		type: $ => choice(
			$.primitive_type,
			$.array_type,
			$.pair_type
		),

		primitive_type: $ => choice(

		),

		array_type: $ => seq(
			$.primitive_type,
			repeat("")
		),

		pair_type: $ => seq(

		),

		identifier: $ => /[a-z]+/

	}
})
